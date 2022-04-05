package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for renderer.ImageWriter class
 */

class ImageWriterTest {


    /**
     * Test method for {@link ImageWriter#writeToImage()}.
     * creates a grid image in green
     */
    @Test
    void testWriteToImage() {
        ImageWriter imageWriter = new ImageWriter("testgreen1",800,500);
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 500; j++) {
                // 800/16 = 50 , 500/10 = 50
                if (i % 50 == 0 || j % 50 == 0) {
                    imageWriter.writePixel(i, j, Color.BLACK);
                }
                else {
                    imageWriter.writePixel(i, j, Color.GREEN);
                }


            }
        }
        imageWriter.writeToImage();
    }
}