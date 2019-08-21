package com.epam.task1_6.planes;

import com.epam.task1_6.entity.Tetrahedron;

public interface InterfaceForCalculatingRelation {
    boolean isTouchingTetrahedron(Tetrahedron tetrahedron);
    boolean canBeCalculated(Tetrahedron tetrahedron);
    double getVolumeOfPart(Tetrahedron tetrahedron);
}
