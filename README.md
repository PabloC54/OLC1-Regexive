# Proyecto 1

** Pablo Fernando Cabrera Pineda  -  201901698 **

## ALFABETO

### TERMINALES

- llaveA			{
- llaveB			}
- conj				CONJ
- dospuntos			:
- id				<identificador>
- flecha			->
- puntocoma			;
- virgulilla			~
- coma				,
- concatenacion			.
- disyuncion			|
- cerradura_kleene		*
- cerradura_positiva		+
- cerradura_bool		?
- comillas			"
- string			<texto>


- porcentajes			%%\n%%
- abrir_comentario		//
- cerrar_comentario		\n
- abrir_comentario_mult		<!
- cerrar_comentario_mult	!>
    
### NO TERMINALES

- S
- C
- DEFINICION_CONJUNTO
- DEFINICION_EXPRESION_REGULAR
- EXPRESION_REGULAR
- COMENTARIO
- COMENTARIO_MULT
- COMPARACION
- ESCAPE


## GRAMÁTICA

Estado inicial = llaveA S porcentajes C llaveB


S ~~> DEFINICION_CONJUNTO
    | DEFINICION_EXPRESION_REGULAR
    | 
    | COMENTARIO
    | COMENTARIO_MULT
    | ɛ


C ~~> dlskd C
    | SENTENCIA_IF
    | ɛ
    

DEFINICION_CONJUNTO ~~> conj dospuntos id flecha EXPRESION_REGULAR S
		      
DEFINICION_EXPRESION_REGULAR ~~> id flecha EXPRESION_REGULAR puntocoma S

COMENTARIO ~~> abrir_comentario T cerrar_comentario S

COMENTARIO_MULT ~~> abrir_comentario_mult T cerrar_comentario_mult S
           