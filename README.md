raytracer_orig
==============

This is the original ray tracer (java) I wrote in Spring 2010 for Computer Graphics.  The assignment details can be found <a href="http://www.ccs.neu.edu/course/cs4300/s10/HW6/HW6.htm">here</a>.  

![3sCheck.rts](https://imgur.com/TrTnv0I)

<h2>Summary</h2>

The purpose of this project was to create a ray tracer, which is a process that renders a 2d image based on a 3d scene.  Essentially, the ray tracer works by casting a ray for each pixel in 2d space into 3d space.  The lighting (and thus color) of the pixel is calculated based on the ray's interaction with the 3d scene.  This process is in contrast to rasterization which takes a somewhat opposite approach to rendering in that the 3d scene is projected onto the 2d space. This is done by iterating through each polygon, calculating each pixels lighting and drawing it in the 2d rendering.


<h2>Usage</h2>

The visible geometry in our 3d scene consists of spheres, triangles, and planes.  In addition there are also lighting constructs which consist of ambient, point and directional.  The input format is described in detail <a href="http://www.ccs.neu.edu/course/cs4300/s10/HW6/HW6.html#input-file-format">here</a>.

There are three input files included in the repo: 1s.rts, 3s.rts and 3sCheck.rts.  The first is a single sphere on a plane, the second is three consecutive spheres on the same plane, and the third is the same three spheres on a checkboard floor made from a triangle mesh.

To run the ray tracer, do a basic java compilation on HomeworkSix.java followed by an execution of the resultant class file with one of the input files piped in.  For example...

javac HomeworkSix.java <br/>
java HomeworkSix < resources/1s.rts

Each input file will have different execution times proportional to the amount of geometry in the scene. The execution time will also depend greatly on your systems CPU capabilities.  While this is a graphical application, the actual ray tracing computation is done on the CPU.  On a modern machine you can expect the execution time to be (probably) subsecond for 1s.rts, a couple seconds for 3s.rts, and (hopefully) under a minute for 3sCheck.rts.

When you first run 1s.rts you'll notice that it looks a little weird, though it is correct. You can cross reference it with Professor Vona's <a href="http://www.ccs.neu.edu/course/cs4300/s10/HW6/HW6.html#one-sphere">solution</a>.  The bottom portion of the sphere is being lit by light reflecting off the plane, whereas the top is only being lit by the light source above.  This causes a hard contrast where the bottom appears unnaturally bright.  Keep in mind the scene is not intended to be something you would actually see in real life.  3sCheck.rts will look significantly less unnatural.	
 
 Also note that the shadow and diffuse lighting 3s and 3sCheck are slightly off from the <a href="http://www.ccs.neu.edu/course/cs4300/s10/HW6/HW6.html#three-spheres">reference solutions</a>.  I intend to fix this in a future version.

<h2>Future Work</h2>

Ray tracing is capable of rendering much more realistic scenes than rasterization, but at the cost of a much higher algorithmic complexity.  Therefore, ray tracing generally has not been used in real-time rendering situations.  However, unlike rasterization, ray tracing algorithms have high potential for parrellization.  With the advent of GPGPU programming, it is likely that real-time ray tracing will be a viable option going forward.

I have taken an interest in GPGPU programming and intend to explore this problem space in the future.  I plan to move forward with this ray tracer as a basis for exploring parrellel programming.  The first step towards doing this will be to convert this project to C as that is the natural language for OpenCL and CUDA work (not to mention its just plain faster than Java).  After that I will take the areas that can be parrellized and implement that in OpenCL or CUDA.  Beyond that I will attempt to optimize the code as much as possible which will likely require researching more advanced algorithms etc. 
