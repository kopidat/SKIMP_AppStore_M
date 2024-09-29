package skimp.member.store.implementation;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import m.client.android.library.core.common.MorpheusApplication;
import skimp.member.store.BaseActivity;

public class ExtendApplication extends MorpheusApplication {
	private static final String TAG = BaseActivity.class.getSimpleName();

	public static final boolean sCheckMDM = true;

	public ExtendApplication() {
//		createLogoutChannel();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		createLogoutChannel();
	}

	private String createLogoutChannel() {
		Log.i(TAG, "createLogoutChannel()");

		String channelId = "logout alarm channel id";
		String channelName = "Logout 알람";

		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) {
			return null;
		}

		int importance = NotificationManager.IMPORTANCE_HIGH;
		NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
		notificationChannel.setDescription("");
		notificationChannel.enableLights(true);
		notificationChannel.setLightColor(Color.RED);
		notificationChannel.enableVibration(true);
		notificationChannel.setVibrationPattern(new long[]{0, 500, 1000});
		Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		AudioAttributes audioAttributes = new AudioAttributes.Builder()
				.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
				.setUsage(AudioAttributes.USAGE_NOTIFICATION)
				.build();
		notificationChannel.setSound(defaultSoundUri, audioAttributes);

		final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.createNotificationChannel(notificationChannel);

		return channelId;
	}

}
