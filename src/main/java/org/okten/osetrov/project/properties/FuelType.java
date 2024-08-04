package org.okten.osetrov.project.properties;

import lombok.Data;

import java.util.List;

@Data
public class FuelType {
    private String name;
    private List<String> variants;
}

// Головний конфігураційний клас (ReferenceDataProperties) відповідає за
// зв'язування з конфігураційним файлом, тоді як вкладені структури (Fuel) можуть бути
// звичайними Java класами, що відображаються як списки або інші типи даних у головному класі.
