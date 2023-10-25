import { Spinner } from "./Spinner";

export function Button({ apiProgress, disabled, children }) {
  return (
    <button disabled={apiProgress || disabled} className="btn btn-primary">
      {apiProgress && <Spinner sm={true} />}
      {children}
    </button>
  );
}
