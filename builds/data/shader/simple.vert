
#version 330 core

layout (location = 0) in vec2 XY;
layout (location = 1) in vec2 UV;

uniform vec2 Translate;
uniform float Rotation; // Not using matricies, much faster in 2D to use one value
uniform vec2 Scale;
uniform float Z;

out vec2 TextureUV;
out vec3 FragPosition;

void main()
{

	float Cosine = cos(Rotation);
	float Sine = sin(Rotation);

	gl_Position = vec4(XY, Z, 1.0);

	// What a mouthful

	gl_Position = vec4((((Scale.x * XY.x * Cosine) - (-Scale.y * XY.y * Sine) + Translate.x) * 2.0 / 700.0) - 1.0, (((Scale.x * XY.x * Sine) + (-Scale.y * XY.y * Cosine) + Translate.y) * 2.0 / 700.0) - 1.0, Z, 1.0);
	TextureUV = UV;
	FragPosition = gl_Position.xyz;

}