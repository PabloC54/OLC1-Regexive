# Gramática

## Alfabeto

### Símbolos terminales

| Token | Lexema | Patrón |
| :-: | :-: | :-: |
| L | **_letra_** | [a-zA-ZñÑ] |
| D | **_digito_** | [0-9] |
| A | **_ASCII_** | [\x20-\x2F\x3A-\x40\x5B-\x7D] |
| blanco | espacio, tab | [\040\t] |
| L_set | conjunto de **_letras_** | (L(,L)+)\|([a-zñ]~[a-zñ])\|([A-ZÑ]~[A-ZÑ]) |
| D_set | conjunto de **_digitos_** | (D(,D)+)\|(D~D) |
| A_set | conjunto de **_ASCII_** | (A(,A)+)\|(A~A) |
| llaveA | { | { |
| llaveB | } | } |
| dospuntos | : | : |
| puntocoma | ; | ; |
| flecha | -> | -> |
| concat | . | . |
| disy | \| | \| |
| cerr_kleene | \* | \* |
| cerr_positiva | + | + |
| cerr_bool | ? | ? |
| especial | **_caracter especial_** | (\\n)\|(\\')\|(\\") |
| porcentajes | %% | %% |
| conj | conj | conj |
| id | **_identificador_** | L(L\|D\|\_)\* |
| string | **_cadena_** | "(.\|blanco)\*" |
| comentario | comentario de una linea | //(.\|blanco)\*\n$ |
| comentario_multilinea | comentario de **_n_** lineas | <!(.\|blanco\|\n)\*!> |

### Símbolos no terminales

- INI
- S
- C
- DEFINICION_CONJUNTO
- CONJUNTO
- DEFINICION_EXPRESION_REGULAR
- EXPRESION_REGULAR
- TERMINO
- COMPARACION

## Sintáxis

```sh
Estado inicial = INI

INI => llaveA S porcentajes C llaveB

S => DEFINICION_CONJUNTO S
   | DEFINICION_EXPRESION_REGULAR S
   | ɛ

C => DEFINICION_CONJUNTO C
   | COMPARACION C
   | ɛ

DEFINICION_CONJUNTO => conj dospuntos id flecha CONJUNTO puntocoma

CONJUNTO => L_set
          | D_set
          | A_set

DEFINICION_EXPRESION_REGULAR => id flecha EXPRESION_REGULAR puntocoma

EXPRESION_REGULAR => concat EXPRESION_REGULAR EXPRESION_REGULAR
                   | disy EXPRESION_REGULAR EXPRESION_REGULAR
                   | cerr_kleene EXPRESION_REGULAR
                   | cerr_positiva EXPRESION_REGULAR
                   | cerr_bool EXPRESION_REGULAR
                   | TERMINO

TERMINO => L
		 | string
         | llaveA id llaveB
         | especial

COMPARACION => id dospuntos string puntocoma
```
