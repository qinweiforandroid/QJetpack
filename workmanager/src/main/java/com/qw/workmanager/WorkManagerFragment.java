package com.qw.workmanager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.qw.utils.Trace;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by qinwei on 2019-09-04 10:47
 * email: qinwei_it@163.com
 */
public class WorkManagerFragment extends Fragment implements View.OnClickListener {
    private TextView mWorkMessageLabel;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            mWorkMessageLabel.append("\n" + msg.obj.toString());
        }
    };

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.work_manager_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.mWorkSingleBtn).setOnClickListener(this);
        view.findViewById(R.id.mWorkSingleConstraintsBtn).setOnClickListener(this);
        view.findViewById(R.id.mWorkSingleInitialDelaysBtn).setOnClickListener(this);
        view.findViewById(R.id.mWorkSingleDataBtn).setOnClickListener(this);
        view.findViewById(R.id.mWorkPeriodicBtn).setOnClickListener(this);
        mWorkMessageLabel = (TextView) view.findViewById(R.id.mWorkMessageLabel);
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.mWorkSingleBtn) {
            simpleWork();
        } else if (id == R.id.mWorkSingleConstraintsBtn) {
            constraints();
        } else if (id == R.id.mWorkSingleInitialDelaysBtn) {
            initialDelays();
        } else if (id == R.id.mWorkSingleDataBtn) {
            simpleWorkData();
        } else if (id == R.id.mWorkPeriodicBtn) {
            periodic();
        }
    }

    private void periodic() {
        PeriodicWorkRequest saveRequest =
                new PeriodicWorkRequest.Builder(SimpleWork.class, 30, TimeUnit.MINUTES)
                        .build();
        AtomicInteger i = new AtomicInteger(0);
        WorkManager.getInstance(getContext()).getWorkInfoByIdLiveData(saveRequest.getId())
                .observe(this, workInfo -> {
//                    i.getAndIncrement();
//                    if (i.get() == 3) {
//                        WorkManager.getInstance(getContext()).cancelWorkById(saveRequest.getId());
//                    }
                });
        WorkManager.getInstance(getContext()).enqueueUniquePeriodicWork("periodic", ExistingPeriodicWorkPolicy.KEEP, saveRequest);
    }

    /**
     * 传入参数
     */
    private void simpleWorkData() {
        Data data = new Data.Builder()
                .putString("data", "123")
                .build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(DataWork.class)
                .setInputData(data)
                .build();
        Trace.d("simpleWorkData 传入参数");
        WorkManager.getInstance(getContext()).enqueue(workRequest);
    }

    /**
     * 延迟10秒执行
     */
    private void initialDelays() {
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(SimpleWork.class)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .build();
        Trace.d("initialDelays 延迟10秒执行");
        WorkManager.getInstance(getContext()).getWorkInfoByIdLiveData(workRequest.getId())
                .observe(this, workInfo -> {
                    if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                        Trace.d("initialDelays 执行完毕 currentThread id " + Thread.currentThread().getId());
                    }
                });
        WorkManager.getInstance(getContext()).enqueue(workRequest);
    }

    private void constraints() {
        Constraints constraints = new Constraints.Builder()
                //设备空闲
//                .setRequiresDeviceIdle(true)
                //设备充电
                .setRequiresCharging(true)
                .build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(SimpleWork.class)
                .setConstraints(constraints)
                .build();
        Trace.d("constraints setRequiresDeviceIdle setRequiresCharging");
        WorkManager.getInstance(getContext()).enqueue(workRequest);
    }

    private void simpleWork() {
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(SimpleWork.class)
                .build();
        mWorkMessageLabel.setText("");
        mWorkMessageLabel.append("time:" + System.currentTimeMillis() + " 执行单次任务，立即执行");
        WorkManager.getInstance(getContext()).enqueue(workRequest);
    }

    public static class SimpleWork extends Worker {

        public SimpleWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
            super(context, workerParams);
        }


        @NonNull
        @Override
        public Result doWork() {
            final Message message = Message.obtain();
            message.obj = "time:" + System.currentTimeMillis() + " doWork executed " + Thread.currentThread().getId();
            Trace.d(message.obj.toString());
//            mHandler.sendMessage(message);
            return Result.success();
        }

    }

    public static class DataWork extends Worker {

        public DataWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
            super(context, workerParams);
        }

        @NonNull
        @Override
        public Result doWork() {
            String data = getInputData().getString("data");
            Trace.d("doWork executed " + Thread.currentThread().getId() + " request data:" + data);
            Data outputData = new Data.Builder()
                    .putString("data", "doWork")
                    .build();
            return Result.success(outputData);
        }
    }
}
