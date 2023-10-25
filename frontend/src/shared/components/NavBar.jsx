import logo from "@/assets/boonote.png";
import { useContext } from "react";
import { useTranslation } from "react-i18next";
import { Link } from "react-router-dom";
import { AuthContext } from "../state/context";

export function NavBar() {
  const { t } = useTranslation();
  const authState = useContext(AuthContext);

  const onClickLogout = () => {
    authState.onLogoutSuccess();
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
                  {t("myProfile")}
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
