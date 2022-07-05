package lighting;

import geometries.Geometry;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.awt.Color.getColor;

public class newPhineasImage {

    private Scene scene1 = new Scene("Test scene").setBackground(Color.VERYDARKBLUE);

    private Camera camera1 = new Camera(new Point(0, 50, 2500), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVPSize(150, 150) //
            .setVPDistance(1000);

    private Material material = new Material().setKd(0.5).setKs(0.5).setShininess(300);

    private Color spCL = new Color(160, 100, 0);

    private List<Geometry> createTable(Point center, double h, double w , double size, Color color)
    {
        List<Geometry> geometries = new LinkedList<>();
        List<Point> corners = new LinkedList<>();
        corners.add(center.addPoint(new Point(-w/2,-3,-h/2))); //left
        corners.add(center.addPoint(new Point(-w/2,-3,+h/2 -3))); //front left
        corners.add(center.addPoint(new Point(+w/2-3,-3,+h/2-3))); //front right
        corners.add(center.addPoint(new Point(+w/2-3,-3,-h/2))); // right

        geometries.add(new Polygon(center.addPoint(new Point(-w/2,0,-h/2)), center.addPoint(new Point(-w/2,0,+h/2)), center.addPoint(new Point(+w/2,0,+h/2)), center.addPoint(new Point(+w/2,0,-h/2))).setMaterial(material).setEmission(color.reduce(2)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));

        geometries.add(new Polygon(center.addPoint(new Point(-w/2,-3,-h/2)), center.addPoint(new Point(-w/2,-3,+h/2)), center.addPoint(new Point(+w/2,-3,+h/2)), center.addPoint(new Point(+w/2,-3,-h/2))).setMaterial(material).setEmission(color.reduce(2)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));

        geometries.add(new Polygon(corners.get(0), corners.get(0).addPoint(new Point(0, 3, 0)),corners.get(1).addPoint(new Point(0, 3, 3)), corners.get(1).addPoint(new Point(0, 0, 3))).setMaterial(material).setEmission(color.reduce(2)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));

        geometries.add(new Polygon(corners.get(1).addPoint(new Point(0, 0, 3)), corners.get(1).addPoint(new Point(0, 3, 3)),corners.get(2).addPoint(new Point(3, 3, 3)), corners.get(2).addPoint(new Point(3, 0, 3))).setMaterial(material).setEmission(color.reduce(2)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));

        geometries.add(new Polygon(corners.get(2).addPoint(new Point(3, 0, 3)), corners.get(2).addPoint(new Point(3, 3, 3)),corners.get(3).addPoint(new Point(3, 3, 0)), corners.get(3).addPoint(new Point(3, 0, 0))).setMaterial(material).setEmission(color.reduce(2)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));

        geometries.add(new Polygon(corners.get(3).addPoint(new Point(3, 0, 0)), corners.get(3).addPoint(new Point(3, 3, 0)),corners.get(0).addPoint(new Point(0, 3, 0)), corners.get(0)).setMaterial(material).setEmission(color.reduce(2)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));

        for (Point c : corners) {
            geometries.add(new Polygon(c, c.addPoint(new Point(0, -size, 0)),c.addPoint(new Point(3, -size, 0)), c.addPoint(new Point(3, 0, 0))).setMaterial(material).setEmission(color.reduce(2)) //
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));


            geometries.add(new Polygon(c.addPoint(new Point(0,0,-3)), c.addPoint(new Point(0, -size, -3)),c.addPoint(new Point(3, -size, -3)), c.addPoint(new Point(3, 0, -3))).setMaterial(material).setEmission(color.reduce(2)) //
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));


            geometries.add(new Polygon(c.addPoint(new Point(0,0,-3)), c.addPoint(new Point(0, -size, -3)), c.addPoint(new Point(0, -size, 0)), c).setMaterial(material).setEmission(color.reduce(2)) //
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));


            geometries.add(new Polygon(c.addPoint(new Point(3,0,-3)), c.addPoint(new Point(3,-size,-3)), c.addPoint(new Point(3,-size,0)), c.addPoint(new Point(3,0,0))).setMaterial(material).setEmission(color.reduce(2)) //
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));
        }

        return geometries;
    }

    private Geometry phineas1 = new Triangle(new Point(-30 +10, 0, 100),new Point((-30+7.5)/2 +10, -15,100 ),new Point(23+10, 27, 100)).setMaterial(material).setEmission(new Color(236,188,180).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry phineas2 = new Triangle(new Point(((-30+7.5)/2 +7.5)/2 +10, (-15 - 30)/2, 100),new Point(7.5 +10, -30,100 ),new Point(23 +10, 27, 100)).setMaterial(material).setEmission(new Color(236,188,180).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry phineas3 = new Triangle(new Point(((-30+7.5)/2 +23)/10 +10, (-15 + 27)/10 +10,100 ),new Point(((-30+7.5)/2 +7.5)/2 +10, -22.5, 100),new Point(23 +10, 27, 100)).setMaterial(material).setEmission(new Color(236,188,180).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry hair1 = new Triangle(new Point(17 +10, 20, -1 +100),new Point(10 +10, 40,-3 +100 ),new Point(23 +10, 27, 0 +100)).setMaterial(material).setEmission(new Color(255,102,0).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry eye1 = new Sphere(new Point(3 +10, 13, 5 +100), 8d) //
            .setEmission(Color.WHITE) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry eye2 = new Sphere(new Point(-5 +10, 12, -10 +100), 8d) //
            .setEmission(Color.WHITE) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry r_iris1 = new Sphere(new Point(2 +10, 13, 10 +100), 4d) //1
            .setEmission(Color.BLUE.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry r_iris2 = new Sphere(new Point(-6 +10, 13, -5 +100), 4d) //1
            .setEmission(Color.BLUE.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry pupil1 = new Sphere(new Point(1 +10, 13, 13.5 +100), 1.5) //1
            .setEmission(Color.BLACK.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry pupil2 = new Sphere(new Point(-7 +10, 13, -1.5 +100), 1.5) //1
            .setEmission(Color.BLACK.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt1 = new Triangle(new Point(-1 +10,-38,0 +110),new Point(7.5 +10, -30,0 +100),new Point(16 +10, -38, 0+110)).setMaterial(material).setEmission(new Color(255,90,0).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt2 = new Triangle(new Point(-1 +10,-38,0+110),new Point(-2 +10, -43,0 +110),new Point(16 +10, -38, 0+110)).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry cote1 = new Triangle(new Point(-1 +10,-38,0 +110),new Point(7.5 +10, -30,0 +100),new Point(-1 +10,-38,0 +90)).setMaterial(material).setEmission(new Color(255,90,0).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry cote2 = new Triangle(new Point(16 +10,-38,0 +110),new Point(7.5 +10, -30,0 +100),new Point(16 +10, -38, 0+90)).setMaterial(material).setEmission(new Color(255,90,0).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt10 = new Triangle(new Point(-1 +10,-38,0 +90),new Point(7.5 +10, -30,0 +100),new Point(16 +10, -38, 0+90)).setMaterial(material).setEmission(new Color(255,90,0).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt20 = new Triangle(new Point(-1 +10,-38,0+90),new Point(-2 +10, -43,0 +90),new Point(16 +10, -38, 0+90)).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry cote20 = new Polygon(new Point(-1 +10,-38,0+110),new Point(-2 +10, -43,0 +110),new Point(-2 +10, -43,0 +90), new Point(-1 +10,-38,0+90)).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry cote21 = new Polygon(new Point(16 +10, -38, 0+90),new Point(16 +10, -38, 0+110),new Point(17 +10, -43,0+110 ), new Point(17 +10, -43,0+90 )).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt3 = new Triangle(new Point(-2 +10,-43,0+110),new Point(17 +10, -43,0+110 ),new Point(16 +10, -38, 0+110)).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt4 = new Triangle(new Point(-2 +10,-43,0+110),new Point(-3 +10, -48,0+110 ),new Point(17 +10, -43, 0+110)).setMaterial(material).setEmission(new Color(255,90,0).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry cote30 = new Polygon(new Point(-2 +10,-43,0+110),new Point(-3 +10, -48,0+110 ),new Point(-3 +10,-48,0+90),new Point(-2 +10, -43,0+90 )).setMaterial(material).setEmission(new Color(255,90,0).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry cote31 = new Polygon(new Point(18 +10,-48,0+110),new Point(17 +10, -43,0+110 ),new Point(17 +10,-43,0+90),new Point(18 +10, -48,0+90 )).setMaterial(material).setEmission(new Color(255,90,0).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt30 = new Triangle(new Point(-2 +10,-43,0+90),new Point(17 +10, -43,0+90 ),new Point(16 +10, -38, 0+90)).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt40 = new Triangle(new Point(-2 +10,-43,0+90),new Point(-3 +10, -48,0+90 ),new Point(17 +10, -43, 0+90)).setMaterial(material).setEmission(new Color(255,90,0).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt5 = new Triangle(new Point(18 +10,-48,0+110),new Point(-3 +10, -48,0+110 ),new Point(17 +10, -43, 0+110)).setMaterial(material).setEmission(new Color(255,90,0).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt6 = new Triangle(new Point(-3.5 +10,-53,0+110),new Point(-3 +10, -48,0 +110),new Point(18 +10,-48,0+110)).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt50 = new Triangle(new Point(18 +10,-48,0+90),new Point(-3 +10, -48,0+90 ),new Point(17 +10, -43, 0+90)).setMaterial(material).setEmission(new Color(255,90,0).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt60 = new Triangle(new Point(-3.5 +10,-53,0+90),new Point(-3 +10, -48,0 +90),new Point(18 +10,-48,0+90)).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry cote40 = new Polygon(new Point(-3.5 +10,-53,0+110),new Point(-3 +10, -48,0 +110),new Point(-3 +10,-48,0+90),new Point(-3.5 +10, -53,0+90 )).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry cote41 = new Polygon(new Point(18 +10,-48,0+110),new Point(18 +10,-48,0+90),new Point(17 +10,-53,0+90),new Point(17 +10, -53,0+110 )).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry teeShirt7 = new Triangle(new Point(-3.5 +10,-53,0+110),new Point(18 +10, -48,0+110 ),new Point(18.5 +10, -53, 0+110)).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry teeShirt70 = new Triangle(new Point(-3.5 +10,-53,0+90),new Point(18 +10, -48,0+90 ),new Point(18.5 +10, -53, 0+90)).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry devpant = new Polygon(new Point(-3.5 +10, -53,0 +110),new Point(-3.5 +10,-62,0+110),new Point(18.5 +10, -62, 0+110), new Point(18.5 +10,-53,0+110)).setMaterial(material).setEmission(Color.BLUE.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry pant = new Polygon(new Point(6.5 +10, -90,0 +90),new Point(-3.5 +10,-90,0+90),new Point(-3.5 +10, -53, 0+90), new Point(6.5 +10,-53,0+90)).setMaterial(material).setEmission(Color.BLUE.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry pant1 = new Polygon(new Point(6.5 +10, -90,0 +110),new Point(-3.5 +10,-90,0+110),new Point(-3.5 +10, -53, 0+110), new Point(6.5 +10,-53,0+110)).setMaterial(material).setEmission(Color.BLUE.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry pant0 = new Polygon(new Point(6.5 +10, -90,0 +90),new Point(6.5 +10, -90,0 +110),new Point(6.5 +10,-53,0+110), new Point(6.5 +10,-53,0+90)).setMaterial(material).setEmission(Color.BLUE.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry pant01 = new Polygon(new Point(-3.5 +10, -90,0 +90),new Point(-3.5 +10, -90,0 +110),new Point(-3.5 +10,-53,0+110), new Point(-3.5 +10,-53,0+90)).setMaterial(material).setEmission(Color.BLUE.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry pant02 = new Polygon(new Point(18.5, -90,0 +90),new Point(18.5, -90,0 +110),new Point(18.5,-53,0+110), new Point(18.5,-53,0+90)).setMaterial(material).setEmission(Color.BLUE.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry pant03 = new Polygon(new Point(8.5, -90,0 +90),new Point(8.5, -90,0 +110),new Point(8.5,-53,0+110), new Point(8.5,-53,0+90)).setMaterial(material).setEmission(Color.BLUE.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry pant2 = new Polygon(new Point(18.5 +10,-90,0 +110),new Point(8.5 +10, -90,0 +110 ),new Point(8.5 +10,-53,0 +110), new Point(18.5 +10,-53,0+110)).setMaterial(material).setEmission(Color.BLUE.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry pant3 = new Polygon(new Point(18.5 +10,-90,0 +90),new Point(8.5 +10, -90,0 +90 ),new Point(8.5 +10,-53,0 +90), new Point(18.5 +10,-53,0+90)).setMaterial(material).setEmission(Color.BLUE.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry wood1 = new Triangle(new Point(5, 34,-10 ),new Point(-70,34,-10),new Point(-70, 32, -10)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry wood2 = new Triangle(new Point(5, 32,-10 ),new Point(-70,32,-10),new Point(5, 34, -10)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry wood4 = new Triangle(new Point(5, -32,-10 ),new Point(-70,-32,-10),new Point(-70, -34, -10)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry wood5 = new Triangle(new Point(5, -34,-10 ),new Point(-70,-34,-10),new Point(5, -32, -10)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry wood3 = new Triangle(new Point(5, -32,-10 ),new Point(5,32,-10),new Point(3, 32, -10)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry wood6 = new Triangle(new Point(5, -32,-10 ),new Point(3,32,-10),new Point(3, -32, -10)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry wood7 = new Triangle(new Point(-70, -32,-10 ),new Point(-70,32,-10),new Point(-68, 32, -10)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry wood8 = new Triangle(new Point(-70, -32,-10 ),new Point(-70,32,-10),new Point(-68, -32, -10)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry window = new Polygon(new Point(-65/2 +1.5, -32,-10 ),new Point(-65/2 -1.5,-32,-10),new Point(-65/2 -1.5,32,-10),new Point(-65/2 +1.5, 32, -10)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry poignee = new Sphere(new Point(-65/2, 0, -10), 1d) //
            .setEmission(Color.YELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    // en haut
    private Geometry Wall1 = new Polygon(new Point(150,34,-10), new Point(-150, 34,-10), new Point(-150,150,-10), new Point(150,150,-10)).setMaterial(material).setEmission(new Color(0, 0, 130).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    // en bas
    private Geometry Wall2 = new Polygon(new Point(150,-90,-10), new Point(-150, -90,-10), new Point(-150,-34,-10), new Point(150,-34,-10)).setMaterial(material).setEmission(new Color(0, 0, 130).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    // droit
    private Geometry Wall3 = new Polygon(new Point(150,150,-10), new Point(150, 150,1500), new Point(150,-90,1500), new Point(150,-90,-10)).setMaterial(material).setEmission(new Color(0, 0, 130).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry Wall4 = new Polygon(new Point(150,150,-10), new Point(150, -90,-10), new Point(5,-90,-10.5), new Point(5,150,-10.5)).setMaterial(material).setEmission(new Color(0, 0, 130).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    // Gauche
    private Geometry Wall5 = new Polygon(new Point(-150,150,-10), new Point(-150, -90,-10), new Point(-70,-90,-10.5), new Point(-70,150,-10.5)).setMaterial(material).setEmission(new Color(0, 0, 130).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry Floor = new Polygon(new Point(-150,-90,-10), new Point(-150, -90,1500), new Point(150,-90,1500), new Point(150,-90,-10)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry dalle1 = new Polygon(new Point(-150,-90,-10), new Point(-150, -90,5), new Point(150,-90,5), new Point(150,-90,-10)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    // b&w floor






    private Geometry vase1 = new Polygon(new Point(-60,-25,925), new Point(-50, -25,925), new Point(-50,-5,925), new Point(-60,-5,925)).setEmission(new Color(24,24,24)) //
            .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(300).setKt(0.6));
    private Geometry vase2 = new Polygon(new Point(-60,-25,945), new Point(-50, -25,945), new Point(-50,-5,945), new Point(-60,-5,945)).setEmission(new Color(24,24,24)) //
            .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(300).setKt(0.6));
    private Geometry vase3 = new Polygon(new Point(-60,-25,925), new Point(-60, -25,945), new Point(-60,-5,945), new Point(-60,-5,925)).setEmission(new Color(24,24,24)) //
            .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(300).setKt(0.6));
    private Geometry vase4 = new Polygon(new Point(-50,-25,925), new Point(-50, -25,945), new Point(-50,-5,945), new Point(-50,-5,925)).setEmission(new Color(24,24,24)) //
            .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(300).setKt(0.6));

    private Geometry tige = new Polygon(new Point(-57,-23,930), new Point(-56, -23,930), new Point(-52,-2,930), new Point(-53,-2,930)).setEmission(Color.GREEN) //
            .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(100));

    private Geometry fleur = new Sphere(new Point(-52, 2, 929), 2) //1
            .setEmission(Color.YELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry moon = new Sphere(new Point(-60, 22, -100), 6d) //
            .setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry vessel = new Sphere(new Point(60, -65, 400), 25d).setEmission(new Color(24,24,24)) //
            .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6));


    private Geometry lamp = new Polygon(//front left
            new Point(75, -60, 800),
            new Point(70, -60, 800),
            new Point(70, 25, 800),
            new Point(75, 25, 800))
            .setMaterial(new Material().setKd(0.4).setKs(.9).setShininess(50).setKr(.7).setKt(0))
            .setEmission(Color.BLACK.scale(.5));
  private Geometry lamp1 = new Sphere(new Point(72.5, 33, 800), 10d)
            .setEmission(new Color(24,24,24)) //
            .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6));


    @Test
    public void createImage() {


        scene1._geometries.add(phineas2);
        scene1._geometries.add(eye1);
        scene1._geometries.add(eye2);
        scene1._geometries.add(r_iris1);
        scene1._geometries.add(pupil1);
        scene1._geometries.add(r_iris2);
        scene1._geometries.add(pupil2);
        scene1._geometries.add(hair1);
        scene1._geometries.add(phineas1);
        scene1._geometries.add(phineas3);

        scene1._geometries.add(teeShirt1);
        scene1._geometries.add(teeShirt2);
        scene1._geometries.add(teeShirt10);
        scene1._geometries.add(teeShirt20);
        scene1._geometries.add(teeShirt3);
        scene1._geometries.add(teeShirt4);
        scene1._geometries.add(teeShirt30);
        scene1._geometries.add(teeShirt40);
        scene1._geometries.add(teeShirt5);
        scene1._geometries.add(teeShirt6);
        scene1._geometries.add(teeShirt50);
        scene1._geometries.add(teeShirt60);
        scene1._geometries.add(teeShirt7);
        scene1._geometries.add(teeShirt70);

        scene1._geometries.add(cote1);
        scene1._geometries.add(cote2);
        scene1._geometries.add(cote20);
        scene1._geometries.add(cote21);
        scene1._geometries.add(cote30);
        scene1._geometries.add(cote31);
        scene1._geometries.add(cote40);
        scene1._geometries.add(cote41);

        scene1._geometries.add(devpant);
        scene1._geometries.add(pant);
        scene1._geometries.add(pant1);
        scene1._geometries.add(pant2);
        scene1._geometries.add(pant3);
        scene1._geometries.add(pant0);
        scene1._geometries.add(pant01);
        scene1._geometries.add(pant02);
        scene1._geometries.add(pant03);



        List<Geometry> tablepieces = new LinkedList<>();
        tablepieces = createTable(new Point(-35,-25,925),250,70,68,Color.BROWN);
        for (Geometry g : tablepieces)
            scene1._geometries.add(g);


        scene1._geometries.add(wood1);
        scene1._geometries.add(wood2);
        scene1._geometries.add(wood4);
        scene1._geometries.add(wood5);
        scene1._geometries.add(wood3);
        scene1._geometries.add(wood6);
        scene1._geometries.add(wood7);
        scene1._geometries.add(wood8);
        scene1._geometries.add(moon);

        scene1._geometries.add(lamp);
        scene1._geometries.add(lamp1);
        scene1._geometries.add(window);
        scene1._geometries.add(poignee);
        scene1._geometries.add(Wall1);
        scene1._geometries.add(Wall2);
        scene1._geometries.add(Wall3);
        scene1._geometries.add(Wall4);
        scene1._geometries.add(Wall5);

        scene1._geometries.add(vase1);
        scene1._geometries.add(vase2);
        scene1._geometries.add(vase3);
        scene1._geometries.add(vase4);

        scene1._geometries.add(tige);
        scene1._geometries.add(fleur);

        // fleurs
        scene1._geometries.add(new Sphere(new Point(-52, -0.5, 929), 2d) //1
                .setEmission(Color.RED.reduce(2)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));

        scene1._geometries.add(new Sphere(new Point(-54.5, 2, 929), 2d) //1
                .setEmission(Color.RED.reduce(2)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));

        scene1._geometries.add(new Sphere(new Point(-52, 4.5, 929), 2d) //1
                .setEmission(Color.RED.reduce(2)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));

        scene1._geometries.add(new Sphere(new Point(-49.5, 2, 929), 2d) //1
                .setEmission(Color.RED.reduce(2)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));

        int max = 32;
        int min = -32;
        int maxX = 3;
        int minX = -68;


        // stars
        for (int i = 0; i < 50; ++i) {
            int randomY = new Random().nextInt(max - min + 1) + min;
            int randomX = new Random().nextInt(maxX - minX + 1) + minX;
            Point p1 = new Point(randomX, randomY, -100d);
            Point p2 = new Point(randomX - 0.5, randomY - 1.0, -100d);
            Point p3 = new Point(randomX + 0.5, randomY - 1.0, -100d);
            Point p4 = new Point(randomX - 1, randomY - 3.0, -100d);
            Point p5 = new Point(randomX + 1.5, randomY - 1.0, -100d);
            Point p6 = new Point(randomX - 1.5, randomY - 1.0, -100d); //-30
            Point p7 = new Point(randomX + 1.0, randomY - 3.0, -100d);
            Geometry alienTriangle1 = new Triangle(p1, p2, p3).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
            Geometry alienTriangle2 = new Triangle(p2, p4, p5).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
            Geometry alienTriangle3 = new Triangle(p6, p3, p7).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
            scene1._geometries.add(alienTriangle1);
            scene1._geometries.add(alienTriangle2);
            scene1._geometries.add(alienTriangle3);
        }



        int sign = 1;
        Color floorColor = Color.BLACK;
        for (int j = -10; j<1500; j+=25 ) {
            for (int i = -150; i < 150; i += 25) {

                scene1._geometries.add(new Polygon(new Point(i, -90, j), new Point(i +25 , -90, j), new Point(i + 25, -90, j +25), new Point(i, -90, j + 25)).setMaterial(material).setEmission(floorColor.reduce(2)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));
                sign = -sign;
                if (sign == -1)
                    floorColor = Color.WHITE;
                else
                    floorColor = Color.BLACK;
            }
            sign = -sign;
        }


        scene1._lights.add(new DirectionalLight(spCL, new Vector(60,0,-10)));
        scene1._lights.add(new SpotLight(Color.WHITE, new Point(-35,120,800), new Vector(0, -1, 800)));


        Geometry mirror = new Polygon(new Point(65, -30, -9.5), new Point(90, -17.5, -9.5),
                new Point(90, 27.5, -9.5), new Point(65, 40, -9.5),
                new Point(40, 27.5, -9.5), new Point(40, -17.5, -9.5))//
                .setEmission(new Color(24, 24, 24))
                .setMaterial(new Material().setKr(0.8).setKt(0.8).setShininess(300));

        Geometry bordMirror = new Polygon(new Point(65, -33, -9.5), new Point(93, -19, -9.5),
                new Point(93, 29, -9.5), new Point(65, 43, -9.5),
                new Point(37, 29, -9.5), new Point(37, -19, -9.5))//
                .setEmission(new Color(255, 215, 0));

        scene1._geometries.add(mirror);
        scene1._geometries.add(bordMirror);
        scene1._lights.add(new PointLight(new Color(java.awt.Color.RED), new Point(72.5, 33, 800)));
        scene1._lights.add(new PointLight(new Color(java.awt.Color.YELLOW), new Point(-60, 22, -40)));


        // simple image
        ImageWriter imageWriter2 = new ImageWriter("simplePhineas", 900, 900);
        camera1.setImageWriter(imageWriter2)
                .setMultithreading(3)
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImageMT(); //
        camera1.writeToImage(); //



        // image with antiAliasing
        ImageWriter imageWriterAntiMT = new ImageWriter("phineasAntialiasing16", 900, 900);
        camera1.setImageWriter(imageWriterAntiMT)
                .setMultithreading(3)
                .setAntiAliasing(16)//
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImageMT(); //
        camera1.writeToImage();

        // supersampling image
        ImageWriter imageWriterss = new ImageWriter("phineasSuperSamplingLevel2", 900, 900);
        camera1.setImageWriter(imageWriterss)
                .setMultithreading(3)
                .setsuperSampling(2)//
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImageMT(); //
        camera1.writeToImage();





    }


}
