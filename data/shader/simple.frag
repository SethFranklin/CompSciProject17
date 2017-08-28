
#version 330 core

uniform float Alpha;
uniform sampler2D Texture;

uniform vec3 LightColor;
uniform vec3 LightPosition;

in vec2 TextureUV;
in vec3 FragPosition;

out vec4 Color;

void main()
{

	vec3 LightPos = vec3((LightPosition.x * 2.0 / 700.0) - 1.0, (LightPosition.y * 2.0 / 700.0) - 1.0, LightPosition.z);

	float AmbientStrength = 1f;

	vec3 Ambient = AmbientStrength * LightColor;
	
	float distance    = length(LightPosition.xy - FragPosition.xy) / 100f;
	float attenuation = 1.0 / (1.0f + 0.09f * distance + 0.032f * (distance * distance));    
	
	Ambient *= attenuation;
	
	vec4 TextureSample = texture(Texture, TextureUV);

	Color = vec4(TextureSample.xyz * Ambient, Alpha * TextureSample.w);

}