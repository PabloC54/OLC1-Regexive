# [Regexive](index.md)

# Gramática

## Alfabeto

### Símbolos terminales

|         Token         |            Lexema            |                                  Patrón                                   |
| :-------------------: | :--------------------------: | :-----------------------------------------------------------------------: |
|           L           |         **_letra_**          |                                [a-zA-ZñÑ]                                 |
|           D           |         **_digito_**         |                                   [0-9]                                   |
|           A           |         **_ASCII_**          |                  [\x20-\x2F\x3A-\x40\x5B-\x60\x7B-\x7D]                   |
|        blanco         |         espacio, tab         |                                 [\040\t]                                  |
|          set          |           conjunto           | ((L\|D\|A)\040*(,\040*(L\|D\|A)\040*)+)\|((L\|D\|A)\040*~\040\*(L\|D\|A)) |
|        llaveA         |              {               |                                     {                                     |
|        llaveB         |              }               |                                     }                                     |
|       dospuntos       |              :               |                                     :                                     |
|       puntocoma       |              ;               |                                     ;                                     |
|        flecha         |              ->              |                                 -\040\*>                                  |
|        concat         |              .               |                                     .                                     |
|         disy          |              \|              |                                    \|                                     |
|      cerr_kleene      |              \*              |                                    \*                                     |
|     cerr_positiva     |              +               |                                     +                                     |
|       cerr_bool       |              ?               |                                     ?                                     |
|       especial        |   **_caracter especial_**    |                            (\\n)\|(\\')\|(\\")                            |
|      porcentajes      |              %%              |                                    %%                                     |
|         conj          |             conj             |                                   conj                                    |
|          id           |     **_identificador_**      |                               L(L\|D\|\_)\*                               |
|        string         |         **_cadena_**         |                 ["'](<((\")\|(\n)\|(\'))\|[^\"\n]>)+["']                  |
|      comentario       |   comentario de una linea    |                             //(.\|blanco)\*$                              |
| comentario_multilinea | comentario de **_n_** lineas |                        <!(blanco\|\\n\|[^!>])\*!>                         |

### Símbolos no terminales

- INI
- S
- C
- DEFINICION_CONJUNTO
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
   | error S
   | ɛ

C => DEFINICION_CONJUNTO C
   | COMPARACION C
   | error C
   | ɛ


DEFINICION_CONJUNTO => conj dospuntos id flecha set puntocoma

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
