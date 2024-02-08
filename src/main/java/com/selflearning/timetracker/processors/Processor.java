package com.selflearning.timetracker.processors;

import com.selflearning.timetracker.data.Task;

public interface Processor {

    public void process(Task task);
}
