
#version 330 core

uniform float Alpha;

uniform sampler2D Texture;
uniform sampler2D NormalMap;
uniform sampler2D SpecularMap;

uniform vec3 LightColor;
uniform vec3 LightPosition;

in vec2 TextureUV;
in vec3 FragPosition;

out vec4 Color;

void main()
{

	vec3 LightPos = vec3((LightPosition.x * 2.0 / 700.0) - 1.0, (LightPosition.y * 2.0 / 700.0) - 1.0, LightPosition.z);

	float AmbientStrength = 0.1;

	vec3 Ambient = AmbientStrength * LightColor;
	
	vec3 norm = normalize(texture(NormalMap, TextureUV).xyz * 2.0 - 1.0);
	vec3 lightDir = normalize(LightPos - FragPosition);
	float diff = max(dot(norm, lightDir), 0.0);
	
	vec3 Diffuse = diff * LightColor;
	
	float specularStrength = 5.0 * length(texture(SpecularMap, TextureUV).xyz);
	
	vec3 viewDir = normalize(vec3(0.0, 0.0, 20.0) - FragPosition);
	vec3 reflectDir = reflect(-lightDir, norm);
	
	float spec = pow(max(dot(viewDir, reflectDir), 0.0), 64);
	vec3 Specular = specularStrength * spec * LightColor;  
	
	vec4 TextureSample = texture(Texture, TextureUV);
	
	float distance    = length(LightPos - FragPosition);
	float attenuation = 1.0 / (1.0 + 0.09 * distance + 0.032 * (distance * distance));    
	
	Ambient *= attenuation;
	Diffuse *= attenuation;
	Specular *= attenuation;

	Color = vec4((Ambient + Diffuse + Specular) * TextureSample.xyz, Alpha * TextureSample.w);

}