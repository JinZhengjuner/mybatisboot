package com.jzj.demo.task;

import lombok.Data;

@Data
public class Task {
    private Long taskId;

    private TaskState state = TaskState.INIT;

    public void updateState(ActionType actionType) {
        if (state == TaskState.INIT) {
            if (actionType == ActionType.STAT) {
                state = TaskState.ONGOING;
            }
        } else if (state == TaskState.ONGOING) {
            if (actionType == ActionType.ACHEIEVE) {
                state = TaskState.FINISHED;
                System.out.println("任务完成了");
            }
        } else if (state == TaskState.PAUSED) {
            if (actionType == ActionType.STAT) {
                state = TaskState.ONGOING;
            } else if (state == TaskState.EXPIPRED) {
                state = TaskState.EXPIPRED;

            }
        }
    }
}
