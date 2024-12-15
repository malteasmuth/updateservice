package de.malteasm.updateservice.update.firmware.businessObject;

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

        if(this.major > o.major){
            return 1;
        }

        if(this.major < o.major){
            return -1;
        }

        if(this.minor > o.minor){
            return 1;
        }

        if(this.minor < o.minor){
            return -1;
        }

        if(this.patch > o.patch){
            return 1;
        }

        if(this.patch < o.patch){
            return -1;
        }

        return 0;
    }

    @Override
    public String toString(){
        return this.getMajor() + "." + this.getMinor() + "." + this.getPatch();
    }
}
