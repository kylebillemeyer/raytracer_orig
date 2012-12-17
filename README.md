raytracer_orig
==============

This is the original ray tracer (java) I wrote in Spring 2010 for Computer Graphics.  The assignment details can be found <a href="http://www.ccs.neu.edu/course/cs4300/s10/HW6/HW6.htm">here</a>.  

<h2>Summary</h2>

The purpose of this project was to create a ray tracer, which is a process that renders a 2d image based on a 3d scene.  Essentially, the ray tracer works by casting a ray for each pixel in 2d space into 3d space.  The lighting (and thus color) of the pixel is calculated based on the ray's interaction with the 3d scene.  This process is in contrast to rasterizing, which takes a somewhat opposite approach to rendering in that the 3d scene is projected on the 2d space. This is done by iterating through each world object, calculating each pixels lighting and drawing it in the 2d rendering.  Ray tracing is capable of rendering much more realistic scenes  For simplicities sake, our 3d scene merely consists of spheres and triangles.
