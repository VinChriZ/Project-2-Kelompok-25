import Engine.*;
import Engine.Object;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import javax.xml.stream.events.Characters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class CobaBlender {
    private ArrayList<Vector3f> Collision=new ArrayList<>();
    public int Turn=0;
    public float[] CharPos=new float[3];
    public float[] CharMan=new float[3];
    public Vector3f temp = new Vector3f(CharPos[0],CharPos[1],CharPos[2]);

    public boolean Finish=false;
    public int isFreeLook=0;
    public boolean notHit=true;
    public boolean notHitY=true;
    public boolean notHitRev=true;
    public boolean notHitYRev=true;
    int countTree =3;
    float tempCount = -16f;
    public Vector2f Radian=new Vector2f();

    private Window window =
            new Window
                    (1920, 1080, "Hello World");
    private ArrayList<Object> objects
            = new ArrayList<>();
    private ArrayList<Object> objectsRectangle
            = new ArrayList<>();

    private ArrayList<Object> objectsPointsControl
            = new ArrayList<>();

    private MouseInput mouseInput;

    static float rot = 0f;
    int countDegree = 0;
    Projection projection = new Projection(window.getWidth(), window.getHeight());
    Camera camera = new Camera();
    Camera tempCam = new Camera();

    public void init() {
        camera.setCharsPos(camera.getPosition());
        window.init();
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
        mouseInput = window.getMouseInput();
        camera.setPosition(0, 3f, 0f);
        camera.addRotation((float)Math.toRadians(30f),0f);

        //Charater
        {
            objects.add(new Sphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.0f, 1.0f, 1.0f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.125f,
                    0.125f,
                    0.125f,
                    36,
                    18
            ));

            ObjectLoader objectLoader = new ObjectLoader("resources/Helicopter.fbx", "fbx");
            objects.get(0).setVertices(objectLoader.vertices);
            objects.get(0).setNormal(objectLoader.normals);
            objects.get(0).setIndicies(objectLoader.indicies);


            objects.get(0).scaleObject(0.01f, 0.01f, 0.01f);
            objects.get(0).rotateObject((float)Math.toRadians(180), 0.0f, 1.0f,0.0f);
            objects.get(0).translateObject(0.0f, 0.0f, -6.0f);
            CharPos[0]=0f;
            CharPos[1]=0f;
            CharPos[2]=-6f;

            //Orang
            objects.add(new Sphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.5f, 0.7f, 0.0f, 1.0f),
                    Arrays.asList(0.0f, 0.0f, 0.0f),
                    0.125f,
                    0.125f,
                    0.125f,
                    36,
                    18
            ));

            objectLoader = new ObjectLoader("resources/orang.fbx", "fbx");
            objects.get(1).setVertices(objectLoader.vertices);
            objects.get(1).setNormal(objectLoader.normals);
            objects.get(1).setIndicies(objectLoader.indicies);


            objects.get(1).scaleObject(0.01f, 0.01f, 0.01f);
            objects.get(1).rotateObject((float)Math.toRadians(180), 1.0f, 0.0f,0.0f);
            objects.get(1).translateObject(15.0f, -7.5f, -9.0f);
            CharMan[0]=15.0f;
            CharMan[1]=-7.5f;
            CharMan[2]=-9.0f;
        }


        //Environment
        {
            //tanah
            objects.add(new Sphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.0f, 0.6f, 0.0f, 1.0f),
                    Arrays.asList(0.0f, 1.0f, 0.0f),
                    0.125f,
                    0.125f,
                    0.125f,
                    36,
                    18
            ));

            ObjectLoader objectLoader = new ObjectLoader("resources/tanah.fbx", "fbx");
            objects.get(2).setVertices(objectLoader.vertices);
            objects.get(2).setNormal(objectLoader.normals);
            objects.get(2).setIndicies(objectLoader.indicies);

            objects.get(2).scaleObject(0.02f, 0.01f, 0.02f);
            objects.get(2).translateObject(0.0f, -10f, 0.0f);


            tempCount = -20f;
            float tempz = 0.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());

            tempCount = -18f;
            tempz += 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());

            tempCount = -20f;
            tempz += 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());

            tempCount = -18f;
            tempz += 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());

            tempCount = -20f;
            tempz += 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());

            tempCount = -18f;
            tempz += 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());


            tempCount = -20f;
            tempz += 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());

            tempCount = -18f;
            tempz += 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());

            tempCount = -20f;
            tempz += 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());

            tempCount = -18f;
            tempz += 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);


            tempCount = -20f;
            tempz += 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());


            tempz = 0f;
            tempCount = -18f;
            tempz -= 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());

            tempCount = -20f;
            tempz -= 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());

            tempCount = -18f;
            tempz -= 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());

            tempCount = -20f;
            tempz -= 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());

            tempCount = -18f;
            tempz -= 2.0f;
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());//
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());


            tempCount = -20f;
            tempz -= 2.0f;
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);

            tempCount = -18f;
            tempz -= 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());

            tempCount = -20f;
            tempz -= 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);

            tempCount = -18f;
            tempz -= 2.0f;
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());

            tempCount = -20f;
            tempz -= 2.0f;
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawRandomTree(tempz);
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
            drawMiniTree(tempCount + generateRandomValue(), -5.0f + generateRandomValue(), tempz + generateRandomValue());
        }



    }
    public void drawMiniTree(float x, float y, float z){
        //batang
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.30f, 0.15f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 1.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f,
                36,
                18
        ));

        System.out.println(countTree);
        ObjectLoader objectLoader = new ObjectLoader("resources/batang.fbx", "fbx");
        objects.get(countTree).setVertices(objectLoader.vertices);
        objects.get(countTree).setNormal(objectLoader.normals);
        objects.get(countTree).setIndicies(objectLoader.indicies);

        //Edit
        objects.get(countTree).scaleObject(0.41f, 4.0f,0.41f);
        objects.get(countTree).translateObject(x, y-3f, z);




        countTree +=1;
        //daun
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.3f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 1.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f,
                36,
                18
        ));

        objectLoader = new ObjectLoader("resources/daun.fbx", "fbx");
        objects.get(countTree).setVertices(objectLoader.vertices);
        objects.get(countTree).setNormal(objectLoader.normals);
        objects.get(countTree).setIndicies(objectLoader.indicies);

        //Edit
        objects.get(countTree).scaleObject(0.41f, 0.5f,0.41f);
        objects.get(countTree).translateObject(x, y, z);

        countTree +=1;
        tempCount +=3.0f;
    }
    public void drawRandomTree(float tempz) {
        Random random = new Random();
        int randomValue = random.nextInt(2) + 1;

        if (randomValue % 2 == 0) {
            drawBigTree(tempCount, tempz);
        } else {
            drawMiniTree(tempCount+generateRandomValue(), -5.0f+generateRandomValue(), tempz+generateRandomValue());
        }
    }
    public void drawBigTree(float x, float z){
        //batang
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.30f, 0.15f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f,
                36,
                18
        ));

        ObjectLoader objectLoader = new ObjectLoader("resources/batang.fbx", "fbx");
        objects.get(countTree).setVertices(objectLoader.vertices);
        objects.get(countTree).setNormal(objectLoader.normals);
        objects.get(countTree).setIndicies(objectLoader.indicies);

        //Edit
        objects.get(countTree).scaleObject(0.41f, 5.0f,0.41f);
        objects.get(countTree).translateObject(x, -3.0f, z);
        Collision.add(new Vector3f(x, -3.0f, z));

        countTree +=1;
        //daun
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.3f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f,
                36,
                18
        ));

        objectLoader = new ObjectLoader("resources/daun.fbx", "fbx");
        objects.get(countTree).setVertices(objectLoader.vertices);
        objects.get(countTree).setNormal(objectLoader.normals);
        objects.get(countTree).setIndicies(objectLoader.indicies);

        //Edit
        objects.get(countTree).scaleObject(0.6f, 1.8f,0.6f);
        objects.get(countTree).translateObject(x, -5.0f, z);
        Collision.add(new Vector3f(x, -5.0f, z));

        countTree +=1;
        tempCount +=3.0f;
    }
    public static float generateRandomValue() {
        Random random = new Random();
        int intervalCount = (int) ((1.0 - 0.1) / 0.05) + 1;
        float randomInterval = (float) (random.nextInt(intervalCount) * 0.05);
        float randomValue = (float) (0.1 + randomInterval);
        return randomValue;
    }



    public void input() throws InterruptedException {
        float move = 0.15f;
        if (window.isKeyPressed(GLFW_KEY_C)) {
            if(camera.getLight())
                camera.setLight(false);
            else
                camera.setLight(true);
            //camera.addRotation((float)Math.toRadians(30),0f);
            Thread.sleep(100);
        }
        if (window.isKeyPressed(GLFW_KEY_F)) {
            CheckHitMan(camera.getDirection().x, camera.getDirection().z);
        }

        //TPP
        if(isFreeLook==0){

            camera.addRotation((float)Math.toRadians(-30),0f);
            Radian=new Vector2f(camera.getRotation());
            if (window.isKeyPressed(GLFW_KEY_1)) {
                isFreeLook = 1;
                camera.moveForward(7f);
                camera.moveDown(3f);
            }
            if (window.isKeyPressed(GLFW_KEY_3)) {
                isFreeLook = 2;

            }
            if (window.isKeyPressed(GLFW_KEY_W)) {
                //camera.addRotation((float)Math.toRadians(-30),0f);
                CheckCol(camera.getDirection().x, camera.getDirection().z, 0);
                if (notHit) {
                    camera.moveForward(move);
                    objects.get(0).translateObject(camera.getDirection().x, 0f, camera.getDirection().z);
                    CharPos[0] += camera.getDirection().x;
                    CharPos[2] += camera.getDirection().z;
                }
                notHitYRev=true;
                notHitY=true;
                //camera.addRotation((float)Math.toRadians(30),0f);
            }
            if (window.isKeyPressed(GLFW_KEY_S)) {
                //camera.addRotation((float)Math.toRadians(-30),0f);
                CheckCol(-camera.getDirection().x, -camera.getDirection().z, 1);
                if (notHitRev) {

                    camera.moveBackwards(move);
                    objects.get(0).translateObject(-camera.getDirection().x, 0f, -camera.getDirection().z);
                    CharPos[0] -= camera.getDirection().x;
                    CharPos[2] -= camera.getDirection().z;
                }
                notHitYRev=true;
                notHitY=true;
                //camera.addRotation((float)Math.toRadians(30),0f);
            }

            if (window.isKeyPressed(GLFW_KEY_A)) {
                //camera.addRotation((float)Math.toRadians(-30),0f);
                //objects.get(0).rotateObject((float)Math.toRadians(45),0f,0f,1f);
                CheckCol(-camera.getRight().x, -camera.getRight().z, 2);
                if (notHitY) {
                    camera.moveLeft(move);
                    objects.get(0).translateObject(-camera.getRight().x, 0f, -camera.getRight().z);
                    CharPos[0] -= camera.getRight().x;
                    CharPos[2] -= camera.getRight().z;
                }
                //camera.addRotation((float)Math.toRadians(30),0f);
            }

            if (window.isKeyPressed(GLFW_KEY_D)) {
                //camera.addRotation((float)Math.toRadians(-30),0f);
                CheckCol(camera.getRight().x, camera.getRight().z, 3);
                if (notHitYRev) {
                    camera.moveRight(move);
                    objects.get(0).translateObject(camera.getRight().x, 0f, camera.getRight().z);
                    CharPos[0] += camera.getRight().x;
                    CharPos[2] += camera.getRight().z;
                }
                //camera.addRotation((float)Math.toRadians(30),0f);
            }

            if (window.isKeyPressed(GLFW_KEY_UP)) {
                camera.moveUp(move);
            }
            if (window.isKeyPressed(GLFW_KEY_DOWN)) {
                camera.moveDown(move);
            }

            if (mouseInput.isLeftButtonPressed()) {
                // camera.addRotation((float)Math.toRadians(-30),0f);
                Vector2f displayVec = window.getMouseInput().getDisplVec();
                camera.moveForward(6f);
                camera.addRotation((float) Math.toRadians(displayVec.x * 0.1f)*0,
                        (float) Math.toRadians(displayVec.y * 0.1f));
                CharRot((float) Math.toRadians(displayVec.y * 0.1f), 0f, -1f, 0f);
                camera.moveBackwards(6f);


            }
            camera.addRotation((float)Math.toRadians(30),0f);
        }

        //FPP
        else if(isFreeLook==1){
            camera.addRotation((float)Math.toRadians(-30),0f);
            Radian=new Vector2f(camera.getRotation());

            if (window.isKeyPressed(GLFW_KEY_2)) {
                isFreeLook = 0;
                camera.moveBackwards(7f);
                camera.moveUp(3f);
            }
            if (window.isKeyPressed(GLFW_KEY_3)) {
                isFreeLook = 2;
            }
            if (window.isKeyPressed(GLFW_KEY_W)) {

                //camera.addRotation((float)Math.toRadians(-30),0f);
                CheckCol(camera.getDirection().x, camera.getDirection().z, 0);
                if (notHit&notHitYRev&notHitY) {
                    camera.moveForward(move);
                    objects.get(0).translateObject(camera.getDirection().x, 0f,camera.getDirection().z);
                    CharPos[0] += camera.getDirection().x;
                    CharPos[2] += camera.getDirection().z;
                }

                //camera.addRotation((float)Math.toRadians(30),0f);
            }
            if (window.isKeyPressed(GLFW_KEY_S)) {
                //camera.addRotation((float)Math.toRadians(-30),0f);
                CheckCol(-camera.getDirection().x, -camera.getDirection().z, 1);
                if (notHitRev) {

                    camera.moveBackwards(move);
                    objects.get(0).translateObject(-camera.getDirection().x, 0f, -camera.getDirection().z);
                    CharPos[0] -= camera.getDirection().x;
                    CharPos[2] -= camera.getDirection().z;
                }
                notHitYRev=true;
                notHitY=true;
                //camera.addRotation((float)Math.toRadians(30),0f);
            }

            if (window.isKeyPressed(GLFW_KEY_A)) {
                //camera.addRotation((float)Math.toRadians(-30),0f);
                //objects.get(0).rotateObject((float)Math.toRadians(45),0f,0f,1f);
                CheckCol(-camera.getRight().x, -camera.getRight().z, 2);
                if (notHitY) {
                    camera.moveLeft(move);
                    objects.get(0).translateObject(-camera.getRight().x, 0f, -camera.getRight().z);
                    CharPos[0] -= camera.getRight().x;
                    CharPos[2] -= camera.getRight().z;
                }
                //camera.addRotation((float)Math.toRadians(30),0f);
            }
            if (window.isKeyPressed(GLFW_KEY_D)) {
                //camera.addRotation((float)Math.toRadians(-30),0f);
                CheckCol(camera.getRight().x, camera.getRight().z, 3);
                if (notHitYRev) {
                    camera.moveRight(move);
                    objects.get(0).translateObject(camera.getRight().x, 0f, camera.getRight().z);
                    CharPos[0] += camera.getRight().x;
                    CharPos[2] += camera.getRight().z;
                }
                //camera.addRotation((float)Math.toRadians(30),0f);
            }

            if (window.isKeyPressed(GLFW_KEY_UP)) {
                camera.moveUp(move);
            }
            if (window.isKeyPressed(GLFW_KEY_DOWN)) {
                camera.moveDown(move);
            }

            if (mouseInput.isLeftButtonPressed()) {
                // camera.addRotation((float)Math.toRadians(-30),0f);
                Vector2f displayVec = window.getMouseInput().getDisplVec();
                camera.moveBackwards(1f);
                camera.addRotation((float) Math.toRadians(displayVec.x * 0.1f)*0,
                        (float) Math.toRadians(displayVec.y * 0.1f));
                CharRot((float) Math.toRadians(displayVec.y * 0.1f), 0f, -1f, 0f);
                camera.moveForward(1f);
            }
            camera.addRotation((float)Math.toRadians(30),0f);
        }

        //Free
        else if(isFreeLook==2){
            camera.addRotation((float)Math.toRadians(-30),0f);
            if (window.isKeyPressed(GLFW_KEY_1)) {
                //camera.setViewMatrix(matCam);
                camera.setPosition(CharPos[0],CharPos[1],CharPos[2]);
                camera.setRotation(0,Radian.y);

                camera.moveForward(1);
                isFreeLook = 1;
            }
            if (window.isKeyPressed(GLFW_KEY_2)) {
                camera.setPosition(CharPos[0],CharPos[1],CharPos[2]);
                camera.setRotation(0,Radian.y);
                camera.moveBackwards(6f);
                camera.moveUp(3f);


                isFreeLook = 0;
            }
            if (window.isKeyPressed(GLFW_KEY_W)) {
                camera.moveForward(move);
            }
            if (window.isKeyPressed(GLFW_KEY_S)) {
                camera.moveBackwards(move);
            }

            if (window.isKeyPressed(GLFW_KEY_A)) {
                camera.moveLeft(move);
            }

            if (window.isKeyPressed(GLFW_KEY_D)) {
                camera.moveRight(move);
            }

            if (window.isKeyPressed(GLFW_KEY_Q)) {
                camera.moveUp(move);
            }
            if (window.isKeyPressed(GLFW_KEY_E)) {
                camera.moveDown(move);
            }
            if (mouseInput.isLeftButtonPressed()) {
                Vector2f displayVec = window.getMouseInput().getDisplVec();
                camera.addRotation((float) Math.toRadians(displayVec.x * 0.1f),
                        (float) Math.toRadians(displayVec.y * 0.1f));
            }
            camera.addRotation((float)Math.toRadians(30),0f);
        }

        camera.moveForward(move);
        camera.moveBackwards(move);
        notHit = true;
        notHitY = true;
        notHitRev = true;
        notHitYRev = true;
        //objects.get(0).rotateObject((float)Math.toRadians(-45),0f,0f,1f);
    }

    public void CheckCol(float x,float y,int temp){
        for (Vector3f vector3f : Collision) {
            //double hypotenuse = Math.sqrt(Math.pow((vector3f.x - (CharPos[0] + x)), 2) + Math.pow((vector3f.z - (CharPos[2] + y)), 2));
            double hypotenuse = Math.sqrt(Math.pow((vector3f.x - (CharPos[0] + x)), 2) + Math.pow((vector3f.z - (CharPos[2] + y)), 2));

            if (hypotenuse < 2.9f)
                if(temp==0&notHitRev)
                    notHit=false;
                else if(temp==1&notHit)
                    notHitRev=false;
                else if(temp==2&notHitYRev)
                    notHitY=false;
                else if(temp==3&notHitY)
                    notHitYRev=false;

        }

    }

    public void CheckHitMan(float x,float y){

            double hypotenuse = Math.sqrt(Math.pow((CharMan[0] - (CharPos[0] + x)), 2) + Math.pow((CharMan[2]- (CharPos[2] + y)), 2));

            if (hypotenuse < 2.0f){
                System.out.println("GAME SELESAI");
                window.setOpen(false);
            }


    }



    public void CharRot(float rad,float x,float y,float z){
        objects.get(0).translateObject(-CharPos[0],-CharPos[1],-CharPos[2]);
        objects.get(0).rotateObject(rad, x, y, z);
        objects.get(0).translateObject(CharPos[0],CharPos[1],CharPos[2]);
    }

    public void loop() throws InterruptedException {
        while (window.isOpen()) {
            window.update();
            glClearColor(0.0f,
                    0.0f, 0.5f,
                    0.0f);
            GL.createCapabilities();

            input();

            //code
            for (Object object : objects) {
                object.draw(camera, projection,temp);
            }

            glDisableVertexAttribArray(0);

            glfwPollEvents();
        }
    }

    public void run()throws InterruptedException{

        init();
        loop();
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public static float getRot() {
        return rot;
    }

    public static void main(String[] args)throws InterruptedException {

        new CobaBlender().run();
    }
}