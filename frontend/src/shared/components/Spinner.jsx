export function Spinner(props) {
  const { sm } = props;
  return (
    <span
      className={`spinner-border ${sm ? "spinner-border-sm" : ""}`}
      area-hidden="true"
    ></span>
  );
}
