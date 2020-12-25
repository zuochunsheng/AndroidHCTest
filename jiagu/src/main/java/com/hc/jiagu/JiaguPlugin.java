package com.hc.jiagu;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.api.ApplicationVariant;
import com.android.build.gradle.api.BaseVariantOutput;

import org.gradle.api.Action;
import org.gradle.api.DomainObjectSet;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import java.io.File;

public class JiaguPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        final JiaguExt jiagu = project.getExtensions().create("jiagu",JiaguExt.class);

        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(final Project project) {
                //String username = jiagu.getUsername();
                //System.out.println(username);

                //AppExtension android = project.getExtensions().getByName("android");
                AppExtension android = project.getExtensions().getByType(AppExtension.class);

                // 变种
                android.getApplicationVariants().all(new Action<ApplicationVariant>() {
                    @Override
                    public void execute(ApplicationVariant applicationVariant) {
                        // debug /release
                        final String name = applicationVariant.getName();

                        applicationVariant.getOutputs().all(new Action<BaseVariantOutput>() {
                            @Override
                            public void execute(BaseVariantOutput baseVariantOutput) {
                                //apk文件
                                File outputFile = baseVariantOutput.getOutputFile();
                                project.getTasks().create("jiagu"+name,JiaguTask.class,outputFile,jiagu);

                            }
                        });

                    }
                });
            }
        });

    }
}
