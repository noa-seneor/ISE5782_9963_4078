package lighting;

import primitives.Color;

/**
 * Abstract class representing light
 */
abstract class Light {
    private Color _intensity;

    protected Light(Color intensity)
    {
        _intensity = intensity;
    }

    /**
     * get light intensity
     * @return
     */
    public Color getIntensity() {
        return _intensity;
    }

}
