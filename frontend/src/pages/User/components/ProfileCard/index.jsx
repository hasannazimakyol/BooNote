import { Button } from "@/shared/components/Button";
import { useAuthState } from "@/shared/state/context";
import { useState } from "react";
import { ProfileImage } from "@/shared/components/ProfileImage";
import { UserEditForm } from "./UserEditForm";
import { useTranslation } from "react-i18next";

export function ProfileCard({ user }) {
  const authState = useAuthState();
  // const authState = useSelector((store) => store.auth);
  const [editMode, setEditMode] = useState(false);
  const { t } = useTranslation();

  const isEditButtonVisible = !editMode && authState.id === user.id;

  const visibleUsername =
    authState.id === user.id ? authState.username : user.username;

  return (
    <div className="card">
      <div className="card-header text-center">
        <ProfileImage width={200} />
      </div>
      <div className="card-body text-center">
        {!editMode && <span className="fs-3 d-block">{visibleUsername}</span>}
        {isEditButtonVisible && (
          <Button onClick={() => setEditMode(true)}>{t("edit")}</Button>
        )}
        {editMode && <UserEditForm setEditMode={setEditMode} />}
      </div>
    </div>
  );
}
