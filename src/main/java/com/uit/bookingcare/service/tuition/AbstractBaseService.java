package com.uit.bookingcare.service.tuition;

import com.uit.bookingcare.service.IService;
import com.uit.bookingcare.utils.MessageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public abstract class AbstractBaseService<Input, Output> implements IService<Input, Output> {

    /**
     * Message Helper.
     */
    @Autowired
    protected MessageHelper messageHelper;

    @Override
    @Transactional
    public Output execute(Input input) {
        try {
            preExecute(input);
            log.info("Validation input success.");
            return doing(input);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public Output execute() {
        return this.execute(null);
    }

    public void preExecute(Input input) {}

    public abstract Output doing(Input input);
}
