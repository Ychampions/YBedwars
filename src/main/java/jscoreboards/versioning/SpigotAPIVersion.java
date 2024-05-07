package jscoreboards.versioning;

import jscoreboards.abstraction.InternalObjectiveWrapper;
import jscoreboards.abstraction.InternalTeamWrapper;
import jscoreboards.version.*;
import jscoreboards.abstraction.InternalObjectiveWrapper;
import jscoreboards.abstraction.InternalTeamWrapper;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;

public enum SpigotAPIVersion {
  v1_8(ObjectiveWrapper_v1_8_v1_12.class, TeamWrapper_v1_8_v1_11.class, 0),
  v1_9(ObjectiveWrapper_v1_8_v1_12.class, TeamWrapper_v1_8_v1_11.class, 1),
  v1_10(ObjectiveWrapper_v1_8_v1_12.class, TeamWrapper_v1_8_v1_11.class, 2),
  v1_11(ObjectiveWrapper_v1_8_v1_12.class, TeamWrapper_v1_8_v1_11.class, 3);

  SpigotAPIVersion(
      Class<? extends InternalObjectiveWrapper> internalObjectiveWrapperClass,
      Class<? extends InternalTeamWrapper> internalTeamWrapperClass,
      int index
  ) {
    this.internalObjectiveWrapperClass = internalObjectiveWrapperClass;
    this.internalTeamWrapperClass = internalTeamWrapperClass;
    this.index = index;
  }

  Class<? extends InternalObjectiveWrapper> internalObjectiveWrapperClass;
  Class<? extends InternalTeamWrapper> internalTeamWrapperClass;
  int index;

  /**
   * The current server version this server is running on.
   *
   * If it's not a part of this enum, it will default to using the last version in the enum.
   */
  private static final SpigotAPIVersion current;

  public static SpigotAPIVersion getCurrent() {
    return current;
  }

  public boolean lessThan(SpigotAPIVersion otherVersion) {
    return this.index < otherVersion.index;
  }

  static {
    String serverPackageVersionString = Bukkit.getServer().getClass().getPackage().getName()
        .split("\\.")[3]; // ex v1_16_R3

    String[] components = serverPackageVersionString.split("_");
    StringBuilder built = new StringBuilder();
    for (int i = 0; i < components.length; i++) {
      if (i >= 2) break; // R3 or anything after, skip. We're using regular Spigot API and don't need to worry about these kinds of issues
      built.append(components[i]);
      if (i != 1) built.append("_");
    }

    SpigotAPIVersion versionToAssign;

    try {
      versionToAssign = SpigotAPIVersion.valueOf(built.toString());
    } catch (IllegalArgumentException ignored) {
      versionToAssign = SpigotAPIVersion.v1_11;

      Bukkit.getLogger().warning("=================================");
      Bukkit.getLogger().warning("Your version of Spigot (package " + serverPackageVersionString + " / search " + built.toString() + ") is not officially supported or tested by JScoreboards.");
      Bukkit.getLogger().warning("Proceed with caution, and report bugs at https://github.com/JordanOsterberg/JScoreboards. Thanks!");
      Bukkit.getLogger().warning("=================================");
    }

    current = versionToAssign;
  }

  public InternalObjectiveWrapper makeObjectiveWrapper() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return internalObjectiveWrapperClass.getDeclaredConstructor().newInstance();
  }

  public InternalTeamWrapper makeInternalTeamWrapper() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return internalTeamWrapperClass.getDeclaredConstructor().newInstance();
  }
}
