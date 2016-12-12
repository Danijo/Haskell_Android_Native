#include "runhugsApp.h"
#include <stdio.h>
#include <jni.h>
#include <stdlib.h>
#define HUGS_SERVER 1
#include "server.h"
//#include "server2.h"
#include <string.h>



#define ANSWER(a) ((*env)->NewStringUTF(env, a))

int REDUCTION_LIMIT = 100000;
int STRING_LENGTH = 100;
int BIG_LENGTH = 10000;


jstring
Java_com_haskellapp_MainActivity_evalHugsExpr( JNIEnv* env,
                                                  jobject thiz, jstring input, jint useroutlim, jint userlimred)
{

	REDUCTION_LIMIT = userlimred;
	//int SIZE = 100000;
	//return ANSWER(REDUCTION_LIMIT);
	const char *user_input = (*env)->GetStringUTFChars(env, input, 0);

	char* hugs_argv[] = {"hugs"};
	int hugs_argc = -1;

	//return ANSWER(initHugsServer (hugs_argc,hugs_argv));


	HugsServerAPI* hugs = initHugsServer (hugs_argc,hugs_argv);

	if (hugs == NULL){
		return ANSWER("Error initialization Hugs interpretar");
	}

	hugs->setOutputEnable(0);


	char* err = hugs -> clearError();
	if (err) {
		return ANSWER(err);
		/*
		char string[SIZE];
		freopen("/dev/null", "a", stdout);
		setbuf(stdout, string);
		char *error3 = strcat(err,"-");
		char *error2 = strcat(error3,string);
		return ANSWER(error2);
		*/
	}

    char string[STRING_LENGTH];
    char *string_to_compile1 = strcpy(string,"show ( ");
    char *string_to_compile2 = strncat(string_to_compile1,user_input, STRING_LENGTH - 20);
    char *final_string = strcat(string_to_compile2," ) :: String");
    hugs->pushHVal(hugs->compileExpr("Prelude",final_string));

    char *result = hugs->evalString();

	err = hugs -> clearError();

	if (err) {
		return ANSWER(err);
	}


    int output_limit = useroutlim;
    int result_lenght = strlen(result);

    if (result == ""){
    	result_lenght = 0;
    }

    char target[BIG_LENGTH];
    char snum[10];

	if (result_lenght > output_limit){

		assert(BIG_LENGTH > output_limit);
		memset(target, 0, sizeof(target));
		strncpy(target, result, output_limit);
		target[output_limit] = '\0';
		char *target_char = target;
		char *result_with_points = strcat(target_char,"...");
		result = result_with_points;

	}

	//shutdownHugsServer(hugs);
    return ANSWER(result);



    exit(0);

}
    // check();




