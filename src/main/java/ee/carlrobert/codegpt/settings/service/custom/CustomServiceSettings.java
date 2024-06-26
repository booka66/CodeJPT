package ee.carlrobert.codegpt.settings.service.custom;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import ee.carlrobert.codegpt.credentials.CustomServiceCredentialManager;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

@State(
    name = "CodeGPT_CustomServiceSettings",
    storages = @Storage("CodeGPT_CustomServiceSettings.xml"))
public class CustomServiceSettings implements PersistentStateComponent<CustomServiceSettingsState> {

  private CustomServiceSettingsState state = new CustomServiceSettingsState();

  @Override
  @NotNull
  public CustomServiceSettingsState getState() {
    return state;
  }

  @Override
  public void loadState(@NotNull CustomServiceSettingsState state) {
    this.state = state;
  }

  public static CustomServiceSettingsState getCurrentState() {
    return getInstance().getState();
  }

  public static CustomServiceSettings getInstance() {
    return ApplicationManager.getApplication().getService(CustomServiceSettings.class);
  }

  public boolean isModified(CustomServiceForm form) {
    return !form.getCurrentState().equals(state)
        || !StringUtils.equals(
        form.getApiKey(),
        CustomServiceCredentialManager.getInstance().getCredential());
  }
}