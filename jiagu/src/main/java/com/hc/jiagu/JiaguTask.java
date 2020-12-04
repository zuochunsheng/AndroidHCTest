package com.hc.jiagu;

import com.hc.jiagu.JiaguExt;
import org.gradle.api.Action;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.process.ExecSpec;
import java.io.File;
import javax.inject.Inject;

public class JiaguTask extends DefaultTask {

    private final File apk;
    private final JiaguExt jiaguExt;

    @Inject
    public JiaguTask(File apk, JiaguExt jiaguExt){
        this.apk = apk;
        this.jiaguExt = jiaguExt;
        setGroup("jiagu");
    }

    @TaskAction
    public void a(){
        //Runtime.getRuntime().exec()
        getProject().exec(new Action<ExecSpec>() {
            @Override
            public void execute(ExecSpec execSpec) {
                //360 jiagu
                execSpec.commandLine("java","-jar",jiaguExt.getJiaguToolPath(),"-login",
                        jiaguExt.getUsername(),jiaguExt.getPassword());

            }
        });

        getProject().exec(new Action<ExecSpec>() {
            @Override
            public void execute(ExecSpec execSpec) {
                execSpec.commandLine("java","-jar",jiaguExt.getJiaguToolPath(),"-importsign",
                        jiaguExt.getStoreFile(),jiaguExt.getStorePassword(),
                        jiaguExt.getKeyAlias(),jiaguExt.getKeyPassword());

            }
        });

        getProject().exec(new Action<ExecSpec>() {
            @Override
            public void execute(ExecSpec execSpec) {
                //360 jiagu
                execSpec.commandLine("java","-jar",jiaguExt.getJiaguToolPath(),"-jiagu",
                      apk.getAbsolutePath(),apk.getParentFile().getAbsolutePath(),"-autosign");

            }
        });
    }
}
