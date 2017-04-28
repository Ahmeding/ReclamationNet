
package com.geek7aggart.gestion2reclamation.utils;

import com.geek7aggart.gestion2reclamation.Model.Intervention;

import java.util.List;

/**
 * Created by BARA' on 09/05/2016.
 */
public interface IIntervention {
    public long newIntervention(Intervention intervention);
    public boolean updateIntervention(Intervention intervention);
    public void deleteIntervention(Intervention intervention);

    public List<Intervention> getAllIntervention();
}
