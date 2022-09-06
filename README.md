# ImageResizing
This was a project for university. It is a program for resizing an image in consideration of its content.

This project was created for a university assignment for the shortest path problem. The basic framework for
this project was given to me. During rescaling, the algorithm tries to find the path of pixels through the
with the least contrast. Removing these pixels would result in the smallest loss of information. This
repeated until the desired width is reached. The image is modeled as an acyclic, weighted, directed grid
graph. The weight of the pixels to other pixels is determined by their contrast and the search is done
through a topological algorithm.
