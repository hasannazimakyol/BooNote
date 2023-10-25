import { Outlet } from "react-router-dom";
import { LanguageSelector } from "./shared/components/LanguageSelector";
import { NavBar } from "./shared/components/NavBar";
import { AuthenticationContext } from "./shared/state/context";

function App() {
  // const [authState, setAuthState] = useState({
  //   id: 0,
  // });
  // const onLoginSuccess = (data) => {
  //   setAuthState(data);
  // };

  return (
    <>
      <AuthenticationContext>
        {/* <NavBar authState={authState} /> */}
        <NavBar />
        <div className="container mt-3">
          {/* <Login onLoginSuccess={onLoginSuccess} /> */}
          <Outlet />
          <LanguageSelector />
        </div>
      </AuthenticationContext>
    </>
  );
}

export default App;
