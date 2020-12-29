package ru.sahlob.logic.persistance.scripts.tehnical;

/**
 * Добавлять новые скрипты исключительно в конец, иначе слетит база данных.
 */
public enum ScriptNames {
 START,

 //create game
 COUNT_THEMES,
 GAME_THEMES,

 COUNT_QUESTIONS,
 GAME_QUESTIONS,

 COUNT_ANSWERS,
 GAME_ANSWERS,


 // play game
 PLAY,
// OPPONENT_CHOOSE,
 PLUG,
 CREATED_GAMES,
 GAME_NAME,
 PLAYER_ID,
 PLAY_WITH_FRIENDS,
 START_QUESTION_PRICE,
 STEP_QUESTION_PRICE,
 CREATE_ROOM,
 ALL_GAMES,
 ROOM,
 JOINT_ROOM,
 START_GAME,
 SELECT_QUESTION
}
