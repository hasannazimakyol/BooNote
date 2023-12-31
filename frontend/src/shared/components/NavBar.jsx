import logo from "@/assets/boonote.png";
import { useTranslation } from "react-i18next";
import { Link } from "react-router-dom";
import { useAuthDispatch, useAuthState } from "../state/context";
import { useDispatch, useSelector } from "react-redux";
import { logoutSuccess } from "../state/redux";
import { ProfileImage } from "./ProfileImage";

export function NavBar() {
  const { t } = useTranslation();
  const authState = useAuthState();
  const dispatch = useAuthDispatch();
  // const authState = useSelector((store) => store.auth);
  // const dispatch = useDispatch();

  const onClickLogout = () => {
    dispatch({ type: "logout-success" });
    // dispatch(logoutSuccess());
  };

  return (
    <nav className="navbar navbar-expand bg-body-tertiary shadow-sm">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/">
          <img src={logo} width={50} />
          Boonote
        </Link>
        <ul className="navbar-nav">
          {authState.id === 0 && (
            <>
              <li className="nav-item">
                <Link className="nav-link" to="/login">
                  {t("login")}
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/signup">
                  {t("signUp")}
                </Link>
              </li>
            </>
          )}
          {authState.id > 0 && (
            <>
              <li className="nav-item">
                <Link className="nav-link" to={`/user/${authState.id}`}>
                  <ProfileImage width={30} />
                  <span className="ms-1">{authState.username}</span>
                </Link>
              </li>
              <li className="nav-item">
                <span
                  className="nav-link"
                  role="button"
                  onClick={onClickLogout}
                >
                  {t("logout")}
                </span>
              </li>
            </>
          )}
        </ul>
      </div>
    </nav>
  );
}
