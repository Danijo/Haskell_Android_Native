/* --------------------------------------------------------------------------
 * Definition of the Hugs server API
 *
 * Copyright (c) The University of Nottingham and Yale University, 1994-1997.
 * All rights reserved. See NOTICE for details and conditions of use etc...
 * Hugs version 1.4, April 1997
 * ------------------------------------------------------------------------*/

#ifndef Args
# if HAVE_PROTOTYPES
#  define Args(x) x
# else
#  define Args(x) ()
# endif
#endif /* !defined Args */

typedef int HVal;     /* Haskell values are represented by stable pointers */

typedef struct _HugsServerAPI {
    char* (*clearError     ) Args((void));
    void  (*setHugsArgs    ) Args((int, char**));
    int   (*getNumScripts  ) Args((void));
    void  (*reset          ) Args((int));
    void  (*setOutputEnable) Args((unsigned));
    void  (*changeDir      ) Args((char*));
    void  (*loadProject    ) Args((char*));     /* obsolete */
    void  (*loadFile       ) Args((char*));
    HVal  (*compileExpr    ) Args((char*,char*));

    void  (*lookupName     ) Args((char*,char*)); /* push values onto stack*/
    void  (*mkInt          ) Args((int));
    void  (*mkString       ) Args((char*));

    void  (*apply          ) Args((void));      /* manipulate top of stack */

    int   (*evalInt        ) Args((void));      /* evaluate top of stack   */
    char* (*evalString     ) Args((void));
    int   (*doIO           ) Args((void));

    HVal  (*popHVal        ) Args((void));      /* pop stack               */
    void  (*pushHVal       ) Args((HVal));      /* push back onto stack    */
    void  (*freeHVal       ) Args((HVal));
} HugsServerAPI;

/* type of "initHugsServer" function */
DLLEXPORT(HugsServerAPI*) initHugsServer Args((Int, String[]));
DLLEXPORT(Void) shutdownHugsServer Args((HugsServerAPI*));

/* ------------------------------------------------------------------------*/
