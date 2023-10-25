import { useContext, useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { Input } from "@/shared/components/Input";
import { Button } from "@/shared/components/Button";
import { login } from "./api";
import { Alert } from "@/shared/components/Alert";
import { AuthContext } from "@/shared/state/context";
import { useNavigate } from "react-router-dom";

export function Login({ onLoginSuccess }) {
  const authState = useContext(AuthContext);
  const { t } = useTranslation();
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [generalError, setGeneralError] = useState();
  const [errors, setErrors] = useState({});
  const [apiProgress, setApiProgress] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    setErrors((lastErrors) => {
      return {
        ...lastErrors,
        email: undefined,
      };
    });
  }, [email]);

  useEffect(() => {
    setErrors((lastErrors) => {
      return {
        ...lastErrors,
        password: undefined,
      };
    });
  }, [password]);

  const onSubmit = async (event) => {
    event.preventDefault();
    setGeneralError();
    // setSuccessMessage();
    setApiProgress(true);

    try {
      const response = await login({
        email,
        password,
      });
      authState.onLoginSuccess(response.data.user);
      // setSuccessMessage(response.data.message);

      navigate("/");
    } catch (axiosError) {
      if (axiosError.response?.data) {
        if (axiosError.response.data.status === 400) {
          setErrors(axiosError.response.data.validationErrors);
        } else {
          setGeneralError(axiosError.response.data.message);
        }
      } else {
        setGeneralError(t("genericError"));
      }
    } finally {
      setApiProgress(false);
    }
  };

  return (
    <div className="container">
      <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
        <form onSubmit={onSubmit} className="card">
          <div className="text-center card-header">
            <h1>{t("login")}</h1>
          </div>
          <div className="card-body">
            <Input
              id="email"
              label={t("email")}
              error={errors.email}
              onChange={(event) => setEmail(event.target.value)}
            />
            <Input
              id="password"
              label={t("password")}
              type="password"
              onChange={(event) => setPassword(event.target.value)}
            />
            {generalError && <Alert styleType="danger">{generalError}</Alert>}
            <div className="text-center">
              <Button disabled={!email || !password} apiProgress={apiProgress}>
                {t("login")}
              </Button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}
