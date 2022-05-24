package primitives;

/**
 * Class representing Material
 */
public class Material {
    public Double3 _kD = Double3.ZERO;
    public Double3 _kS =Double3.ZERO;
    public Double3 _kR = Double3.ZERO;
    public Double3 _kT =Double3.ZERO;
    public int _nShininess = 0;

    /**
     * set diffusive factor
     * @param kD
     * @return
     */
    public Material setKd(Double3 kD)
    {
        _kD = kD;
        return this;
    }

    /**
     * set specular factor
     * @param kS
     * @return
     */
    public Material setKs(Double3 kS)
    {
        _kS = kS;
        return this;
    }

    /**
     * set diffusive factor
     * @param kD
     * @return
     */
    public Material setKd(double kD)
    {
        _kD = new Double3(kD);
        return this;
    }

    /**
     * set specular Factor
     * @param kS
     * @return
     */
    public Material setKs(double kS)
    {
        _kS = new Double3(kS);
        return this;
    }

    /**
     * set diffusive factor
     * @param kT
     * @return
     */
    public Material setKt(double kT)
    {
        _kT = new Double3(kT);
        return this;
    }

    /**
     * set specular Factor
     * @param kR
     * @return
     */
    public Material setKr(double kR)
    {
        _kR = new Double3(kR);
        return this;
    }


    /**
     * set shininess factor
     * @param nShininess
     * @return
     */
    public Material setShininess(int nShininess)
    {
        _nShininess = nShininess;
        return this;
    }
}
