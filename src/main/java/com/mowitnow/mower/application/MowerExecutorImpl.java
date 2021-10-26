package com.mowitnow.mower.application;


import com.mowitnow.mower.ApplicationProperties;
import com.mowitnow.mower.domain.Command;
import com.mowitnow.mower.domain.Mower;
import com.mowitnow.mower.domain.MowerData;
import com.mowitnow.mower.domain.MowerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MowerExecutorImpl implements MowerExecutor, ApplicationRunner {


    private final ApplicationProperties applicationProperties;

    private final MowerRepository mowerRepository;


    @Override
    public void run(ApplicationArguments args) {

        if (this.applicationProperties.isAutoStart()) {

            this.start();

        }
    }

    @Override
    public void start() {

        List<MowerData> mowers = this.mowerRepository.getMowers();

        mowers.forEach(mowerData -> {

            MowerExecutorImpl.log.info("******************************** Mower Start ************************************");

            this.execute(mowerData);

            MowerExecutorImpl.log.info("******************************** Mower end ************************************");
        });

        MowerExecutorImpl.log.info("******************************** Result ************************************");

        mowers.stream().map(MowerData::getMower).forEach(o -> MowerExecutorImpl.log.info(o.toString()));

    }

    private void execute(MowerData mowerData) {

        Objects.requireNonNull(mowerData).getCommands()
                .forEach(command -> this.handleCommand(command, mowerData.getMower()));

    }

    private void handleCommand(Command command, Mower mower) {

        MowerExecutorImpl.log.info("Handling mower command {}", command);

        switch (command) {

            case D:
                mower.right();
                break;

            case G:
                mower.left();
                break;

            case A:
                mower.move();
                break;
            default:
                MowerExecutorImpl.log.error("Sorry! i don't know how to handle mower command {}", command);
        }
    }
}
