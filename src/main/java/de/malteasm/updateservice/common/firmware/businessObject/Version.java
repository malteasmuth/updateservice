package de.malteasm.updateservice.common.firmware.businessObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Version implements Comparable<Version> {

    private int major;
    private int minor;
    private int patch;

    @Override
    public int compareTo(Version o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (major != o.major) {
            return Integer.compare(this.major, o.major);
        }
        if (minor != o.minor) {
            return Integer.compare(this.minor, o.minor);
        }
        return Integer.compare(this.patch, o.patch);
    }

    @Override
    public String toString(){
        return this.getMajor() + "." + this.getMinor() + "." + this.getPatch();
    }
}
