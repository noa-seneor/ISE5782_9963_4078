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

import java.util.Random;

import static java.awt.Color.getColor;

public class PhineasImage {

    private Scene scene1 = new Scene("Test scene").setBackground(Color.VERYDARKBLUE);

    private Camera camera1 = new Camera(new Point(0, 50, 2300), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVPSize(150, 150) //
            .setVPDistance(1000);
    private Color spCL = Color.YELLOW; // Sphere test Color of Light
    private Vector trDL = new Vector(-2, -2, -2); // Triangles test Direction of Light
    private Material material = new Material().setKd(0.5).setKs(0.5).setShininess(300);

    /**
     private Geometry phineas = new Triangle(new Point(-30, 0, 0),new Point(7.5, -30,0 ),new Point(23, 27, 0)).setMaterial(material).setEmission(new Color(236,188,180).reduce(2)) //
     .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
     */

    private Geometry phineas1 = new Triangle(new Point(-30, 0, 0),new Point((-30+7.5)/2, -15,0 ),new Point(23, 27, 0)).setMaterial(material).setEmission(new Color(236,188,180).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry phineas2 = new Triangle(new Point(((-30+7.5)/2 +7.5)/2, (-15 - 30)/2, 0),new Point(7.5, -30,0 ),new Point(23, 27, 0)).setMaterial(material).setEmission(new Color(236,188,180).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry phineas3 = new Triangle(new Point(((-30+7.5)/2 +23)/10, (-15 + 27)/10,0 ),new Point(((-30+7.5)/2 +7.5)/2, -22.5, 0),new Point(23, 27, 0)).setMaterial(material).setEmission(new Color(236,188,180).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry hair1 = new Triangle(new Point(17, 20, -1),new Point(10, 40,-3 ),new Point(23, 27, 0)).setMaterial(material).setEmission(new Color(255,102,0).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry eye1 = new Sphere(new Point(3, 13, 5), 8d) //
            .setEmission(Color.WHITE) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry eye2 = new Sphere(new Point(-5, 12, -10), 8d) //
            .setEmission(Color.WHITE) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry r_iris1 = new Sphere(new Point(2, 13, 10), 4d) //1
            .setEmission(Color.BLUE.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry r_iris2 = new Sphere(new Point(-6, 13, -5), 4d) //1
            .setEmission(Color.BLUE.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry pupil1 = new Sphere(new Point(1, 13, 13.5), 1.5) //1
            .setEmission(Color.BLACK.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
    private Geometry pupil2 = new Sphere(new Point(-7, 13, -1.5), 1.5) //1
            .setEmission(Color.BLACK.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt1 = new Triangle(new Point(-1,-38,0),new Point(7.5, -30,0 ),new Point(16, -38, 0)).setMaterial(material).setEmission(new Color(255,90,0).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt2 = new Triangle(new Point(-1,-38,0),new Point(-2, -43,0 ),new Point(16, -38, 0)).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt3 = new Triangle(new Point(-2,-43,0),new Point(17, -43,0 ),new Point(16, -38, 0)).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt4 = new Triangle(new Point(-2,-43,0),new Point(-3, -48,0 ),new Point(17, -43, 0)).setMaterial(material).setEmission(new Color(255,90,0).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt5 = new Triangle(new Point(18,-48,0),new Point(-3, -48,0 ),new Point(17, -43, 0)).setMaterial(material).setEmission(new Color(255,90,0).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt6 = new Triangle(new Point(-3.5,-53,0),new Point(-3, -48,0 ),new Point(18,-48,0)).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry teeShirt7 = new Triangle(new Point(-3.5,-53,0),new Point(18, -48,0 ),new Point(18.5, -53, 0)).setMaterial(material).setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    //private Geometry pant1 = new Triangle(new Point(-3.5,-53,0),new Point(-3.5, -60,0 ),new Point(6.5,-53,0)).setMaterial(material).setEmission(Color.BLUE.reduce(2)) //
    //.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    //private Geometry pant2 = new Triangle(new Point(-3.5, -60,0 ),new Point(6.5,-53,0),new Point(6.5, -60, 0)).setMaterial(material).setEmission(Color.BLUE.reduce(2)) //
    //.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry pant = new Polygon(new Point(6.5, -90,0 ),new Point(-3.5,-90,0),new Point(-3.5, -53, 0), new Point(6.5,-53,0)).setMaterial(material).setEmission(Color.BLUE.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry pant1 = new Polygon(new Point(18.5,-90,0),new Point(8.5, -90,0 ),new Point(8.5,-53,0), new Point(18.5,-53,0)).setMaterial(material).setEmission(Color.BLUE.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry pant4 = new Triangle(new Point(18.5, -53,0 ),new Point(8.5,-53,0),new Point(8.5, -60, 0)).setMaterial(material).setEmission(Color.BLUE.reduce(2)) //
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

    private Geometry Wall1 = new Polygon(new Point(150,34,-10), new Point(-150, 34,-10), new Point(-150,150,-10), new Point(150,150,-10)).setMaterial(material).setEmission(new Color(0, 0, 130).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry Wall2 = new Polygon(new Point(150,-90,-10), new Point(-150, -90,-10), new Point(-150,-34,-10), new Point(150,-34,-10)).setMaterial(material).setEmission(new Color(0, 0, 130).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry Wall3 = new Polygon(new Point(150,150,-10), new Point(150, 150,1500), new Point(150,-90,1500), new Point(150,-90,-10)).setMaterial(material).setEmission(new Color(0, 0, 130).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry Wall4 = new Polygon(new Point(150,150,-10), new Point(150, -90,-10), new Point(5,-90,-10.5), new Point(5,150,-10.5)).setMaterial(material).setEmission(new Color(0, 0, 130).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry Wall5 = new Polygon(new Point(-150,150,-10), new Point(-150, -90,-10), new Point(-70,-90,-10.5), new Point(-70,150,-10.5)).setMaterial(material).setEmission(new Color(0, 0, 130).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry Floor = new Polygon(new Point(-150,-90,-10), new Point(-150, -90,1500), new Point(150,-90,1500), new Point(150,-90,-10)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry table = new Polygon(new Point(-70,-25,800), new Point(-70, -25,1050), new Point(0,-25,1050), new Point(0,-25,800)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry table1 = new Polygon(new Point(-70,-22,800), new Point(-70, -22,1050), new Point(0,-22,1050), new Point(0,-22,800)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry bordAvant = new Polygon(new Point(-70,-25,1050), new Point(-70, -22,1050), new Point(0,-22,1050), new Point(0,-25,1050)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry bordArriere = new Polygon(new Point(-70,-25,800), new Point(-70, -22,800), new Point(0,-22,800), new Point(0,-25,800)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry bordG = new Polygon(new Point(-70,-25,800), new Point(-70, -25,1050), new Point(-70,-22,1050), new Point(-70,-22,800)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry bordD = new Polygon(new Point(0,-25,800), new Point(0, -25,1050), new Point(0,-22,1050), new Point(0,-22,800)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry pied1 = new Polygon(new Point(-70,-22,1050), new Point(-70, -90,1050), new Point(-67,-90,1050), new Point(-67,-22,1050)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry pied2 = new Polygon(new Point(-70,-22,1047), new Point(-70, -90,1047), new Point(-67,-90,1047), new Point(-67,-22,1047)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry pied3 =  new Polygon(new Point(-70,-22,1047), new Point(-70, -90,1047), new Point(-70,-90,1050), new Point(-70,-22,1050)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry pied4 =new Polygon(new Point(-67,-22,1047), new Point(-67, -90,1047), new Point(-67,-90,1050), new Point(-67,-22,1050)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));



    private Geometry piedD1 = new Polygon(new Point(0,-22,1000), new Point(0, -90,1000), new Point(-3,-90,1000), new Point(-3,-22,1000)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry piedD2 = new Polygon(new Point(-0,-22,997), new Point(-0, -90,997), new Point(-3,-90,997), new Point(-3,-22,997)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry piedD3 =  new Polygon(new Point(-0,-22,997), new Point(-0, -90,997), new Point(-0,-90,1000), new Point(-0,-22,1000)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry piedD4 =new Polygon(new Point(-3,-22,997), new Point(-3, -90,997), new Point(-3,-90,1000), new Point(-3,-22,1000)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry piedB1 = new Polygon(new Point(0,-22,800), new Point(0, -90,800), new Point(-3,-90,800), new Point(-3,-22,800)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry piedB2 = new Polygon(new Point(-0,-22,803), new Point(-0, -90,803), new Point(-3,-90,803), new Point(-3,-22,803)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry piedB3 =  new Polygon(new Point(-0,-22,803), new Point(-0, -90,803), new Point(-0,-90,800), new Point(-0,-22,800)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry piedB4 =new Polygon(new Point(-3,-22,803), new Point(-3, -90,803), new Point(-3,-90,800), new Point(-3,-22,800)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry piedG1 = new Polygon(new Point(-70,-22,800), new Point(-70, -90,800), new Point(-67,-90,800), new Point(-67,-22,800)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry piedG2 = new Polygon(new Point(-70,-22,803), new Point(-70, -90,803), new Point(-67,-90,803), new Point(-67,-22,803)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry piedG3 =  new Polygon(new Point(-70,-22,803), new Point(-70, -90,803), new Point(-70,-90,800), new Point(-70,-22,800)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Geometry piedG4 =new Polygon(new Point(-67,-22,803), new Point(-67, -90,803), new Point(-67,-90,800), new Point(-67,-22,800)).setMaterial(material).setEmission(Color.BROWN.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry moon = new Sphere(new Point(-60, 22, -100), 6d) //
            .setEmission(Color.LIGHTYELLOW.reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

/*
    private Geometry mirror1 = new Triangle(new Point(-68, 32, -9.5), new Point(3, 32, -9.5), new Point(3, -32, -9.5)).setEmission(new Color(10,10,10)) //
            .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6));

    private Geometry mirror2 =  new Triangle(new Point(-68, -32, -9.5), new Point(3, -32, -9.5), new Point(-68, 32, -9.5)).setEmission(new Color(10,10,10)) //
            .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6));

 */


    private Geometry lamp = new Polygon(//front left
            new Point(75, -60, 800),
            new Point(70, -60, 800),
            new Point(70, 25, 800),
            new Point(75, 25, 800))
            .setMaterial(new Material().setKd(0.4).setKs(.9).setShininess(50).setKr(.7).setKt(0))
            .setEmission(Color.BLACK.scale(.5));
    /*
        private Geometry lamp1 = new Sphere(new Point(32.5, 33, 6), 8d) //
                .setEmission(Color.GREEN) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

     */private Geometry lamp1 = new Sphere(new Point(72.5, 33, 800), 10d)
            .setEmission(new Color(24,24,24)) //
            .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6));


    @Test
    public void sphereDirectional() {

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
        scene1._geometries.add(teeShirt3);
        scene1._geometries.add(teeShirt4);
        scene1._geometries.add(teeShirt5);
        scene1._geometries.add(teeShirt6);
        scene1._geometries.add(teeShirt7);
        scene1._geometries.add(pant);
        //scene1._geometries.add(pant2);
        scene1._geometries.add(pant1);
        scene1._geometries.add(pant4);
        scene1._geometries.add(wood1);
        scene1._geometries.add(wood2);
        scene1._geometries.add(wood4);
        scene1._geometries.add(wood5);
        scene1._geometries.add(wood3);
        scene1._geometries.add(wood6);
        scene1._geometries.add(wood7);
        scene1._geometries.add(wood8);
        scene1._geometries.add(moon);
        /*
        scene1._geometries.add(mirror1);
        scene1._geometries.add(mirror2);

         */
        scene1._geometries.add(lamp);
        scene1._geometries.add(lamp1);
        scene1._geometries.add(window);
        scene1._geometries.add(poignee);
        scene1._geometries.add(Wall1);
        scene1._geometries.add(Wall2);
        scene1._geometries.add(Wall3);
        scene1._geometries.add(Wall4);
        scene1._geometries.add(Wall5);
        scene1._geometries.add(Floor);
        scene1._geometries.add(table);
        scene1._geometries.add(table1);
        scene1._geometries.add(bordAvant);
        scene1._geometries.add(bordArriere);
        scene1._geometries.add(bordD);
        scene1._geometries.add(bordG);

        scene1._geometries.add(pied1);
        scene1._geometries.add(pied2);
        scene1._geometries.add(pied3);
        scene1._geometries.add(pied4);

        scene1._geometries.add(piedD1);
        scene1._geometries.add(piedD2);
        scene1._geometries.add(piedD3);
        scene1._geometries.add(piedD4);

        scene1._geometries.add(piedB1);
        scene1._geometries.add(piedB2);
        scene1._geometries.add(piedB3);
        scene1._geometries.add(piedB4);

        scene1._geometries.add(piedG1);
        scene1._geometries.add(piedG2);
        scene1._geometries.add(piedG3);
        scene1._geometries.add(piedG4);


        int max = 32;
        int min = -32;
        int maxX = 3;
        int minX = -68;


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


        //scene1._lights.add(new DirectionalLight(spCL, new Vector(0,-30,-1)));

        /*
        scene1._lights.add( //
                new SpotLight(new Color(java.awt.Color.WHITE),  new Point(-80, -90, -30), new Vector(4, 2, -6))//
                        .setKl(1E-5).setKq(1.5E-7));


         */

        Geometry mirror = new Polygon(new Point(65, -30, -9.5), new Point(90, -17.5, -9.5),
                new Point(90, 27.5, -9.5), new Point(65, 40, -9.5),
                new Point(40, 27.5, -9.5), new Point(40, -17.5, -9.5))//
                .setEmission(new Color(24, 24, 24))
                .setMaterial(new Material().setKr(0.8).setKt(0.8));

        Geometry bordMirror = new Polygon(new Point(65, -33, -9.5), new Point(93, -19, -9.5),
                new Point(93, 29, -9.5), new Point(65, 43, -9.5),
                new Point(37, 29, -9.5), new Point(37, -19, -9.5))//
                .setEmission(new Color(255, 215, 0));

        scene1._geometries.add(mirror);
        scene1._geometries.add(bordMirror);
        scene1._lights.add(new PointLight(new Color(java.awt.Color.RED), new Point(72.5, 33, 800)));
        scene1._lights.add(new PointLight(new Color(java.awt.Color.YELLOW), new Point(-60, 22, -40)));


        ImageWriter imageWriter = new ImageWriter("phineasWithout", 900, 900);
        camera1.setImageWriter(imageWriter)
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage(); //
        camera1.writeToImage(); //

        ImageWriter imageWriterAnti = new ImageWriter("phineasAntialiasing", 900, 900);
        camera1.setImageWriter(imageWriterAnti)
                .setAntiAliasing(4)//
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage(); //
        camera1.writeToImage();
    }


}
