package software_engineering_project.shapes;

import java.io.Serializable;

public abstract class OpenContourShape implements Shape, Serializable {
    private SerializableShape shape;

    @Override
    public abstract void focus();

    @Override
    public abstract void copy(Shape clipboard);
}
