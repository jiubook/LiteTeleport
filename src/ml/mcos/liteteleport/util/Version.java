package ml.mcos.liteteleport.util;

public class Version {
    private final int minor;
    private final int patch;

    public Version(String bukkitVersion) {
        String[] parts = bukkitVersion.split("-", 2)[0].split("\\.");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Unsupported Bukkit version: " + bukkitVersion);
        }
        if ("1".equals(parts[0])) {
            this.minor = parseVersionPart(parts[1]);
            this.patch = parts.length > 2 ? parseVersionPart(parts[2]) : 0;
        } else {
            this.minor = parseVersionPart(parts[0]);
            this.patch = parseVersionPart(parts[1]);
        }
    }

    private static int parseVersionPart(String value) {
        int end = 0;
        while (end < value.length() && Character.isDigit(value.charAt(end))) {
            end++;
        }
        if (end == 0) {
            return 0;
        }
        return Integer.parseInt(value.substring(0, end));
    }

    public int getMinor() {
        return minor;
    }

    public int getPatch() {
        return patch;
    }

    public boolean isLessThan(int minor) {
        return this.minor < minor;
    }

    public boolean isLessThan(int minor, int patch) {
        if (this.minor != minor) {
            return this.minor < minor;
        }
        return this.patch < patch;
    }

    public boolean isLessThanOrEqualTo(int minor) {
        return this.minor <= minor;
    }

    public boolean isLessThanOrEqualTo(int minor, int patch) {
        return this.isLessThan(minor, patch) || this.equals(minor, patch);
    }

    public boolean isGreaterThan(int minor) {
        return this.minor > minor;
    }

    public boolean isGreaterThan(int minor, int patch) {
        if (this.minor != minor) {
            return this.minor > minor;
        }
        return this.patch > patch;
    }

    public boolean isGreaterThanOrEqualTo(int minor) {
        return this.minor >= minor;
    }

    public boolean isGreaterThanOrEqualTo(int minor, int patch) {
        return this.isGreaterThan(minor, patch) || this.equals(minor, patch);
    }

    public boolean equals(int minor) {
        return this.minor == minor;
    }

    public boolean equals(int minor, int patch) {
        return this.minor == minor && this.patch == patch;
    }

    @Override
    public String toString() {
        if (minor >= 26) {
            return minor + "." + patch;
        }
        return "1." + minor + "." + patch;
    }
}
