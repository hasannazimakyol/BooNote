import { Component } from "react";
import { useParams } from "react-router-dom";
import { getUser } from "./api";
import { Alert } from "@/shared/components/Alert";
import { Spinner } from "@/shared/components/Spinner";
import { withTranslation } from "react-i18next";

export class UserClass extends Component {
  state = {
    user: null,
    apiProgress: false,
    error: null,
  };

  loadUser = async () => {
    this.setState({ apiProgress: true });
    try {
      const response = await getUser(this.props.id);
      this.setState({
        user: response.data,
      });
    } catch (axiosError) {
      this.setState({
        error: this.props.t("userNotFound"),
      });
    } finally {
      this.setState({ apiProgress: false });
    }
  };

  async componentDidMount() {
    this.loadUser();
  }

  componentDidUpdate(previousProps, previousState) {
    if (this.props.id !== previousProps.id) {
      this.loadUser();
    }
  }

  componentWillUnmount() {}

  render() {
    return (
      <>
        {this.state.user && <h1>{this.state.user.username}</h1>}
        {this.state.apiProgress && (
          <Alert styleType="secondary" center>
            <Spinner />
          </Alert>
        )}
        {this.state.error && (
          <Alert styleType="danger">{this.state.error}</Alert>
        )}
      </>
    );
  }
}

const UserPageWithTranslation = withTranslation()(UserClass);

export function User() {
  const { id } = useParams();
  return <UserPageWithTranslation id={id} />;
}
