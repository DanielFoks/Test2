package com.example.form.field;

import com.example.form.main.StaticField;
import com.example.form.other.Points;
import com.example.form.other.Position;
import com.example.form.square.MyTimer;
import com.example.form.square.Square;
import com.example.form.square.SquareCombo;
import com.example.form.square.SquareLose;
import com.example.form.square.SquareLow;
import com.example.form.square.SquareNormal;
import com.example.form.square.SquareX;

import java.util.TimerTask;

/**
 * Объект класса Field создает массив указаного размера на котором в случайном месте появляется квадрат.
 */
public class Field {
    private Square[][] arraySquare;
    private int size;
    private MyTimer timerMain;
    private MyTimer timerEdit;
    private MyTimer timerRemove;
    private int row;
    private int col;
    private Position position;
    private MyTimer timerCombo;

    private TimerTask mainTask;
    private TimerTask editTask;
    private TimerTask removeTask;
    private TimerTask comboTask;

    public Field(int size) {
        this.size = size;
        arraySquare = new Square[size][size];
        createSquare();
        removeSquare();
    }

    /**
     * Создает главный таймер.
     * Главный таймер решает когда появляться квадратам.
     */
    private void createSquare() {
        timerMain = new MyTimer(StaticField.StartSpeed);
        mainTask = new TimerTask() {
            @Override
            public void run() {
                createNormalSquare();
                createMinusSquare();
            }
        };
        timerMain.schedules(mainTask);

        timerEdit = new MyTimer(200);
        editTask = new TimerTask() {
            @Override
            public void run() {
                if (!StaticField.combo) {
                    createLoseSquare();
                    createSlowSquare();
                    createXSquare();
                    createComboSquare();
                } else {
                    pause();
                    fieldClear();
                    StaticField.combo = false;

                    for (int i = 0; i < 10; i++) {
                        createNormalSquare();
                    }
                    for (int i = 0; i < 3; i++) {
                        createMinusSquare();
                    }

                    timerCombo = new MyTimer(2500);
                    comboTask = new TimerTask() {
                        @Override
                        public void run() {
                            int minus = 0;
                            for (int i = 0; i < arraySquare.length; i++) {
                                for (int j = 0; j < arraySquare[i].length; j++) {
                                    if (arraySquare[i][j] != null) {
                                        minus = minus + arraySquare[i][j].getPoint();
                                    }
                                }
                            }
                            Points.addPoints(minus * (-1));
                            timerCombo.cancel();
                            fieldClear();
                            pause();
                        }
                    };
                    timerCombo.schedules(comboTask);
                }
            }
        };
        timerEdit.schedules(editTask);
    }

    /**
     * Добавляет в массив поля обычный куб.
     */
    private void createNormalSquare() {
        position = getRandomPosition();
        arraySquare[position.getRow()][position.getColumn()] = new SquareNormal(position, Integer.parseInt(String.valueOf(timerMain.getDelay())), 1, 99);
    }

    /**
     * Добавляет в массив поля куб с отрицательным значением.
     */
    private void createMinusSquare() {
        if ((int) (Math.random() * 2) == 0) {
            position = getRandomPosition();
            arraySquare[position.getRow()][position.getColumn()] = new SquareNormal(position, Integer.parseInt(String.valueOf(timerMain.getDelay())), -99, -1);
        }
        if ((int) (Math.random() * 3) == 0) {
            position = getRandomPosition();
            arraySquare[position.getRow()][position.getColumn()] = new SquareNormal(position, (int) (Math.random() * 1000 + 600), -99, -1);
        }
        if ((int) (Math.random() * 4) == 0) {
            position = getRandomPosition();
            arraySquare[position.getRow()][position.getColumn()] = new SquareNormal(position, (int) (Math.random() * 1000 + 600), -99, -1);
        }
    }

    /**
     * Добавляет в массив поля куб замедления.
     */
    private void createSlowSquare() {
        if ((int) (Math.random() * 200) == 0) {
            position = getRandomPosition();
            arraySquare[position.getRow()][position.getColumn()] = new SquareLow(position, 1000);
        }
    }

    /**
     * Добавляет в массив поля куб проигрыша.
     */
    private void createLoseSquare() {
        if ((int) (Math.random() * 40) == 0) {
            position = getRandomPosition();
            arraySquare[position.getRow()][position.getColumn()] = new SquareLose(position, 3000);
        }
    }

    /**
     * Добавляет в массив поля "X" куб.
     */
    private void createXSquare() {
        if ((int) (Math.random() * 50) == 0) {
            position = getRandomPosition();
            arraySquare[position.getRow()][position.getColumn()] = new SquareX(position, 1000);
        }
    }

    /**
     * Добавляет в массив поля комбо куб.
     */
    private void createComboSquare() {
        if ((int) (Math.random() * 150) == 0) {
            position = getRandomPosition();
            arraySquare[position.getRow()][position.getColumn()] = new SquareCombo(position, 1000);
        }
    }

    /**
     * Выбирает случайно строку и столбец.
     * Если место с такой позицией занято, выбирает новое значение.
     *
     * @return Позиция, на которой создается куб.
     */
    private Position getRandomPosition() {
        do {
            row = (int) (Math.random() * size);
            col = (int) (Math.random() * size);
        } while (arraySquare[row][col] != null);
        return new Position(row, col);
    }

    private void timerMainFaster(int time) {
        timerMain.setDelay(timerMain.getDelay() - time);
        StaticField.speed = Integer.parseInt(String.valueOf(timerMain.getDelay()));
    }

    public void timerMainSlower(int time) {
        timerMain.setDelay(timerMain.getDelay() + time);
        StaticField.speed = Integer.parseInt(String.valueOf(timerMain.getDelay()));
    }


    /**
     * Проходит по всему полю. Если квадрат не пустой то проверяет его время.
     * Если время меньше 0 то удаляет его из поля.
     */
    private void removeSquare() {
        timerRemove = new MyTimer(100);
        removeTask = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < arraySquare.length; i++) {
                    for (int j = 0; j < arraySquare[i].length; j++) {
                        if (arraySquare[i][j] != null) {
                            if (arraySquare[i][j].getTime() <= 0) {
                                arraySquare[i][j].remove();
                                arraySquare[i][j] = null;
                            }
                        }

                    }
                }
            }
        };
        timerRemove.schedules(removeTask);
    }

    public void removeSquare(int row, int col) {
        if (!StaticField.combo) {
            timerMainFaster(10);
        }
        arraySquare[row][col].press();
        arraySquare[row][col] = null;
    }

    public Square[][] getArraySquare() {
        return arraySquare;
    }

    public int getSquareIcon(int row, int col) {
        return arraySquare[row][col].getIcon();
    }

    public String getSquareString(int row, int col) {
        return arraySquare[row][col].getString();
    }

    public void pause() {
        if (!StaticField.pause) {
            timerMain.cancel();
            timerEdit.cancel();
            timerRemove.cancel();
            StaticField.pause = true;

            for (Square[] anArraySquare : arraySquare) {
                for (Square anAnArraySquare : anArraySquare) {
                    if (anAnArraySquare != null) {
                        anAnArraySquare.pause();
                    }
                }
            }

        } else {
            long timerMainDelay = timerMain.getDelay();
            long timerEditDelay = timerEdit.getDelay();
            long timerRemoveDelay = timerRemove.getDelay();

            mainTask = new TimerTask() {
                @Override
                public void run() {
                    createNormalSquare();
                    createMinusSquare();
                }
            };
            removeTask = new TimerTask() {
                @Override
                public void run() {
                    for (int i = 0; i < arraySquare.length; i++) {
                        for (int j = 0; j < arraySquare[i].length; j++) {
                            if (arraySquare[i][j] != null) {
                                if (arraySquare[i][j].getTime() <= 0) {
                                    arraySquare[i][j].remove();
                                    arraySquare[i][j] = null;
                                }
                            }

                        }
                    }
                }
            };
            editTask = new TimerTask() {
                @Override
                public void run() {
                    if (!StaticField.combo) {
                        createLoseSquare();
                        createSlowSquare();
                        createXSquare();
                        createComboSquare();
                    } else {
                        pause();
                        fieldClear();
                        StaticField.combo = false;

                        for (int i = 0; i < 10; i++) {
                            createNormalSquare();
                        }
                        for (int i = 0; i < 3; i++) {
                            createMinusSquare();
                        }

                        timerCombo = new MyTimer(2500);
                        comboTask = new TimerTask() {
                            @Override
                            public void run() {
                                int minus = 0;
                                for (int i = 0; i < arraySquare.length; i++) {
                                    for (int j = 0; j < arraySquare[i].length; j++) {
                                        if (arraySquare[i][j] != null) {
                                            minus = minus + arraySquare[i][j].getPoint();
                                        }
                                    }
                                }
                                Points.addPoints(minus * (-1));
                                timerCombo.cancel();
                                fieldClear();
                                pause();
                            }
                        };
                        timerCombo.schedules(comboTask);
                    }
                }
            };

            timerMain = new MyTimer(timerMainDelay);
            timerMain.schedules(mainTask);
            timerEdit = new MyTimer(timerEditDelay);
            timerEdit.schedules(editTask);
            timerRemove = new MyTimer(timerRemoveDelay);
            timerRemove.schedules(removeTask);
            StaticField.pause = false;

            for (Square[] anArraySquare : arraySquare) {
                for (Square anAnArraySquare : anArraySquare) {
                    if (anAnArraySquare != null) {
                        anAnArraySquare.unPause();
                    }
                }
            }
        }
    }

    public void restart() {
        Points.restart();
        fieldClear();
        timerMain.setDelay(StaticField.StartSpeed);
    }

    public void fieldClear() {
        for (int i = 0; i < arraySquare.length; i++) {
            for (int j = 0; j < arraySquare[i].length; j++) {
                arraySquare[i][j] = null;
            }
        }
    }
}
