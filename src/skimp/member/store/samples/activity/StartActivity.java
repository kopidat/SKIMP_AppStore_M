package skimp.member.store.samples.activity;

import m.client.android.library.core.control.Controller;
import m.client.android.library.core.model.NetReqOptions;
import m.client.android.library.core.view.AbstractActivity;
import m.client.android.library.core.common.CommonLibHandler;
import m.client.android.library.core.common.DataHandler;
import m.client.android.library.core.common.LibDefinitions;
import m.client.android.library.core.common.Parameters;
import m.client.android.library.core.utils.CommonLibUtil;
import m.client.android.library.core.utils.CommonLibUtil.ResourceApdateCallBack;
import m.client.android.library.core.utils.PLog;
import skimp.member.store.R;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * SampleActivity Class
 * 
 * @author 류경민(<a mailto="kmryu@uracle.co.kr">kmryu@uracle.co.kr</a>)
 * @version v 1.0.0
 * @since Android 2.1 <br>
 *        <DT><B>Date: </B>
 *        <DD>2011.04</DD>
 *        <DT><B>Company: </B>
 *        <DD>Uracle Co., Ltd.</DD>
 * 
 * 이 클래스는 네이티브 샘플 시작 액티비티이다. 
 * 네이티브 시작 화면 액티비티를 추가할 경우에는 아래의 샘플을 참조한다.  
 * 
 * Copyright (c) 2001-2011 Uracle Co., Ltd. 
 * 166 Samseong-dong, Gangnam-gu, Seoul, 135-090, Korea All Rights Reserved.
 */
public class StartActivity extends AbstractActivity {
	
	private Activity thisObj;
	ImageView imgView;
	
	public StartActivity() {
		super();
		thisObj = this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// Image view
		imgView = new ImageView(this);  
		RelativeLayout.LayoutParams imgLayoutParams = new RelativeLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, 
    																			FrameLayout.LayoutParams.FILL_PARENT);
		
		imgView.setLayoutParams(imgLayoutParams);
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.start_bg);
		BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
		imgView.setImageDrawable(bitmapDrawable);
		
		// Main Layout
		RelativeLayout mainLayout = new RelativeLayout(this);  
        FrameLayout.LayoutParams mnLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, 
        																		FrameLayout.LayoutParams.FILL_PARENT, 
        																		Gravity.TOP | Gravity.LEFT);  
        mainLayout.setLayoutParams(mnLayoutParams);
        mainLayout.addView(imgView);
		setContentView(mainLayout);
		
		// 웹 페이지 로딩시 발생하는 흰색 배경 처리를 위해 현재 화면을 캡쳐하여 처리한다.
//		ViewTreeObserver vto = imgView.getViewTreeObserver();
//    	vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
//    	 @Override
//    	 public void onGlobalLayout() {
//    		 imgView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//    		 setViewForMovingScreen(imgView);
//    	 }
//    	});
		
    	requestResource();
		// 통신 테스트
//        findViewById(R.id.comm).setOnClickListener(new OnClickListener() {
//        	
//        	@Override
//			public void onClick(View arg0) {
//	        	
//	        	/*
//	    		 * 받은 파라메터
//	    		 */
//	    		Parameters receivedParams = new Parameters(getParamsString());
//	    		System.out.println(">> parameters : [" + receivedParams + "]");
//	    		System.out.println(">> param1 : " + receivedParams.getParam("param1", "UTF-8"));	// URL Decoding
//	    		System.out.println(">> param2 : " + receivedParams.getParam("param2", "UTF-8"));	// URL Decoding
//	    		System.out.println(">> param3 : " + receivedParams.getParam("param3"));	
//	            
//	            /* 
//	             * 요청 전문 데이터 생성
//	             */
//	    		JSONObject sendData = new JSONObject();
//	    		try {
//	    			sendData.put("id","");
//	    			sendData.put("phnmd","android");
//	    			sendData.put("svcnm","morpheus");
//	    			sendData.put("pw","");
//	    			sendData.put("udid","oqwielkdjflijwe901239123");
//	    			sendData.put("phnno","01083775269");
//	    		} catch (JSONException e) {
//	    			PLog.printTrace(e);
//	    		}
//	    		
//	    		/*
//	    		 *  전문 송신에 관한 옵션
//	    		 *  
//	    		 *  encrypt 암호화 여부
//	    		 *  indicator 인디케이터 사용여부
//	    		 *  indicatorMsg 인디케이터 메시지
//	    		 *  dummy 가상화 더미 서버사용여부
//	    		 *  targetServerName 타켓 서버 이름 
//	    		 *  retargetUrl 재접속 URL
//	    		 */
//	    		NetReqOptions netReqOptions = new NetReqOptions();
//	    		
//	    		/*
//	    		 * 데이터 송신 처리
//	    		 * DataHandle 형식의 데이터를 요청한다.
//	    		 * @param stTrCode : 전문 식별 문자열
//	    		 * @param otherInfos : 기타 정보 (리턴 받을 자바스크립트 함수 이름)
//	    		 * @param dhSendData : 송신 데이터(DataHandle 타입)
//	    		 * @param netReqOptions : 전문 옵션
//	    		 */
//	    		netReqOptions.targetServerName = "HTTP_MAIN";  // (중요 : 반드시 AppManifext.xml에서 정의한 타켓 서버 이름을 설정해야 한다.)
//	    		netReqOptions.indicatorMsg = "데이터 요청중입니다..";
//	    		requestData("MOB_EXAM_R002", "", new DataHandler(sendData), netReqOptions);
//	    		
//			}
//		});
//        		
//		
//		// 네이티브 화면으로의 이동
//        findViewById(R.id.moveToNativeAct).setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				
//				// 화면에서 사용할 전역 변수 설정(웹화면에서의 사용:WNSetVariable, WNGetVariable) 
//				CommonLibUtil.setVariable("native_global1", "네이티브 글로별 변수값1");	//WNGetvarible();
//
//				// 프로그램이 설치되어 있는 동안 영구적으로 사용할 수 있는 영역(파일)에 정보 저장(웹화면에서의 사용:WNSetVariableToStorage, WNGetVariableFromStorage)
//				CommonLibUtil.setVariableToStorage("natvie_storage1", "네이티브 저장 변수값1", thisObj.getApplicationContext());
//				
//				// 파라메터 설정
//				Parameters pubParams = new Parameters();
//				
//				// 파라메터 정보를 보내는 경우 
//				Parameters inParam = new Parameters();
//				inParam.putParam("native_param1", "네이티브 파라메터 값1");
//				inParam.putParam("native_param2", "네이티브 바라메터 값2(테&스트)", "UTF-8"); 
//				pubParams.putParam("PARAMETERS", inParam.getParamString());
//				
//				// 화면 방향 설정 
//				// DEFAULT : 단말기에서 기본적으로 제공되는 방향 설정 적용
//			    // PORT : 세로 고정
//			    // LAND : 가로 고정
//			    // ALL : 가로, 세로 방향 변경 가능
//				pubParams.putParam("ORIENT_TYPE", "PORT");
//				
//				// Action type
//				// NEW_SCR : 대상이 되는 화면을 화면 관리 스택에 새롭게 추가한다.
//		        // NO_HISTORY : 대상이 되는 화면을 화면 관리 스택에 추가하지 않는다.
//		        // CLEAR_TOP : 대상이 되는 이전 화면으로 이동한다. 이동시 현재 화면에서 대상 화면 사이의 모든 화면들은 제거된다.
//		    	int actionType = CommonLibUtil.getActionType("NEW_SCR");
//		    	
//		    	// Animation Type
//		    	// DEFALUT : 왼쪽으로 이동되는 슬라이드 효과(SLIDE_LEFT)
//		        // NONE : 애니메이션 효과 없음
//		        // SLIDE_LEFT : 왼쪽으로 이동되는 슬라이드 효과
//		        // SLIDE_RIGHT : 오른쪽으로 이동되는 슬라이드 효과
//		        // SLIDE_TOP : 위쪽으로 이동되는 슬라이드 효과
//		        // SLIDE_BOTTOM : 아래쪽으로 이동되는 슬라이드 효과
//		        // ZOOM_IN : 줌인 효과
//		        // ZOOM_OUT : 줌아웃 효과
//		        // FADE : 페이드 효과
//		        // MODAL_UP : 원본 화면은 고정되어 있고 대상 화면만 위쪽으로 이동되는 슬라이드 효과
//		        // MODAL_DOWN : 원본 화면은 고정되어 있고 대상 화면만 아래쪽으로 이동되는 슬라이드 효과
//		    	
//		    	// 네이티브 화면으로의 이동
//		    	// 타켓 클래스 인덱스는 Definitions.activities 에 정의된 이동할 네이티브 화면의 인덱스 (예 : Definitions.activities.ACTY_SAMPLE2)
//		    	Controller.getInstance().actionMoveActivity(Definitions.activities.ACTY_SAMPLE2, 
//		    												actionType, 
//		    												thisObj, 
//		    												"ZOOM_IN",
//		    												pubParams);
//			}
//		});
//        
//        // UI 웹화면으로 이동
//        findViewById(R.id.moveToWeb).setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				
//				// 화면에서 사용할 전역 변수 설정(웹화면에서의 사용:WNSetVariable, WNGetVariable) 
//				CommonLibUtil.setVariable("web_global1", "네이티브 화면에서 저장되는 전역변수 값1");	//WNGetvarible();
//
//				// 프로그램이 설치되어 있는 동안 영구적으로 사용할 수 있는 영역(파일)에 정보 저장(웹화면에서의 사용:WNSetVariableToStorage, WNGetVariableFromStorage)
//				CommonLibUtil.setVariableToStorage("web_storage1", "네이티브 화면에서 저장되는 영속변수 값1", thisObj.getApplicationContext());
//				
//				// 파라메터 설정
//				Parameters pubParams = new Parameters();
//				
//				// 파라메터 정보를 보내는 경우 
//				Parameters inParam = new Parameters();
//				inParam.putParam("REQUEST_PARAM", "이 메시지는 네이티브 화면에서 보냅니다.");
//				inParam.putParam("param1", "paramValue1");
//				inParam.putParam("param2", "테&스트", "UTF-8"); 
//				pubParams.putParam("PARAMETERS", inParam.getParamString());
//				
//				// 웹 컨테이너에서 보여질 html 페이지
//				pubParams.putParam("TARGET_URL", CommonLibHandler.getInstance().g_strHTMLDirForWeb + "yepp.html");	
//
//				// 화면 방향 설정 
//				// DEFAULT : 단말기에서 기본적으로 제공되는 방향 설정 적용
//			    // PORT : 세로 고정
//			    // LAND : 가로 고정
//			    // ALL : 가로, 세로 방향 변경 가능
//				pubParams.putParam("ORIENT_TYPE", "PORT");
//				
//				// Action type
//				// NEW_SCR : 대상이 되는 화면을 화면 관리 스택에 새롭게 추가한다.
//		        // NO_HISTORY : 대상이 되는 화면을 화면 관리 스택에 추가하지 않는다.
//		        // CLEAR_TOP : 대상이 되는 이전 화면으로 이동한다. 이동시 현재 화면에서 대상 화면 사이의 모든 화면들은 제거된다.
//		    	int actionType = CommonLibUtil.getActionType("NEW_SCR");
//		    	
//		    	// Animation Type
//		    	// DEFALUT : 왼쪽으로 이동되는 슬라이드 효과(SLIDE_LEFT)
//		        // NONE : 애니메이션 효과 없음
//		        // SLIDE_LEFT : 왼쪽으로 이동되는 슬라이드 효과
//		        // SLIDE_RIGHT : 오른쪽으로 이동되는 슬라이드 효과
//		        // SLIDE_TOP : 위쪽으로 이동되는 슬라이드 효과
//		        // SLIDE_BOTTOM : 아래쪽으로 이동되는 슬라이드 효과
//		        // ZOOM_IN : 줌인 효과
//		        // ZOOM_OUT : 줌아웃 효과
//		        // FADE : 페이드 효과
//		        // MODAL_UP : 원본 화면은 고정되어 있고 대상 화면만 위쪽으로 이동되는 슬라이드 효과
//		        // MODAL_DOWN : 원본 화면은 고정되어 있고 대상 화면만 아래쪽으로 이동되는 슬라이드 효과
//		    	
//		    	// 하이브리드 웹화면으로 이동시에는 타겟 클래스 인텍스가 반드시 LibDefinitions.libactivities.ACTY_MAIN 이어야 한다. 
//		    	Controller.getInstance().actionMoveActivity(LibDefinitions.libactivities.ACTY_MAIN, 
//		    												actionType, 
//		    												thisObj, 
//		    												"SLIDE_RIGHT",
//		    												pubParams);
//			}
//        });
//        
//        // 이전 버튼
//        findViewById(R.id.back_button).setOnClickListener(new OnClickListener() {
//        	
//        	@Override
//    		public void onClick(View arg0) {
//        		onBackPressed();
//        	}
//        });
        
	}
	
	@Override
	protected void onStop(){ 
		super.onStop(); 
	
//		PLog.i(CLASS_TAG, "Abstract onStop()"); 
	} 
	 
	@Override
	protected void onResume(){ 
		super.onResume(); 
//		PLog.i(CLASS_TAG, "Abstract onResume()"); 
		
	} 
	 
	@Override
	protected void onStart(){ 
		super.onStart(); 
//		PLog.i(CLASS_TAG, "Abstract onStart()"); 
		
	} 
	 
	@Override
	protected void onRestart(){ 
		super.onRestart(); 
//		PLog.i(CLASS_TAG, "Abstract onRestart()"); 
	} 
	
	@Override
	protected void onNewIntent(Intent intent)
	{
		super.onNewIntent(intent);
//		PLog.i(CLASS_TAG, "Abstract onNewIntent()"); 
	}
	 
	@Override
	protected void onDestroy(){ 
		super.onDestroy(); 
	} 
	
	@Override
    public void onConfigurationChanged(Configuration _newConfig) {
     super.onConfigurationChanged(_newConfig);
     
	}
	
	/**
	 * 네트워크 에러나 기타 에러 발생히 호출된다. 
	 * @param callerServerName 타켓서버이름
	 * @param trCode : 전문 번호 
	 * @param errCode : 에러 코드 
	 * @param errMessage : 에러 메시지
	 */
	@Override
	public void handlingError(String callerServerName, String trCode, String errCode, String errMessage, NetReqOptions netReqOpt) {
		
		PLog.w("CLASS_TAG", thisObj.getClass().getName() + " " + trCode + " " + errCode + " " +  errMessage);
	}

	public void requestResource(){
		CommonLibUtil.ResourceApdateCallBack callback = new ResourceApdateCallBack() {
			
			@Override
			public void cbUpdateResourceFiles(String result, String appInfo) {
				// TODO Auto-generated method stub
				// result => intro.html 페이지로 전달되는 이벤트가 동일하게 String 형태로 전달됨
				// appInfo => Application APK버전을 체크를 위해 추가되었으며 서버에 값을 내려주면 JSON 형태의 data가 String 형태로 전달
				PLog.i("StartActivity", "onSuccess result => " + result + "   => " + appInfo);
				Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						setViewForMovingScreen(getRootView(thisObj));
					}
				}, 300); 	
			}
			@Override
			public void cbUpdateResourceFilesOnProgress(int totalSize, int readSize, int remainingSize, int percentage) {
				// TODO Auto-generated method stub
				super.cbUpdateResourceFilesOnProgress(totalSize, readSize, remainingSize, percentage);
				PLog.i("StartActivity", "totalSize => " + totalSize + "  readSize => " + readSize + 
						"  remainingSize => " + remainingSize + "  percentage => " + percentage);
			}
		};
		
		CommonLibUtil.HttpUpdateResources(this, "HTTP_MAIN", "DEV", callback);
	}
	/**
	 * 데이터 송신 처리
	 * DataHandle 형식의 데이터를 요청한다.
	 * @param stTrCode : 전문 식별 문자열
	 * @param otherInfos : 기타 정보 
	 * @param dhSendData : 송신 데이터(DataHandle 타입)
	 * @param netReqOptions : 전문 옵션
	 */
	@Override
	public void requestData(String stTrCode, String otherInfos, DataHandler dhSendData, NetReqOptions netReqOptions) {
		if (CommonLibHandler.getInstance().requestDataUsingNetworkInfo(stTrCode, otherInfos, dhSendData, this, netReqOptions) == false) {		
			//설정파일에서 서버 이름을 찾을 수 없습니다.
			handlingError("", "-1", "", "설정파일에서 서버 이름을 찾을 수 없습니다.", null);
		}	
	}

	
	/**
	 * 데이터 수신
	 * 데이터를 받은 후 처리하는 부분을 구현해야한다.
	 * 
	 * @param stTrCode : 전문 식별 문자열
	 * @param otherInfos : 기타 정보 
	 * @param dhRecvData : 수신 데이터(DataHandle 타입)
	 * @param tagId : 화면 객체 구분 태그
	 */
	@Override
	public void responseData(int nDataType, String stTrCode, String otherInfos, String dhRecvData, NetReqOptions netReqOpt) {
		CommonLibUtil.responseAppData(stTrCode, otherInfos, dhRecvData, netReqOpt);
	}
	
	/**
	 * 이전 키를 눌렀을때 처리 
	 */
	@Override
	public void onBackPressed() {
		
		// 이전 화면으로 이동
		
//		// 파라메터 설정
//		Parameters backParam = new Parameters();
//		backParam.putParam("backParam1", "backParamValue1");
//		backParam.putParam("backParam2", "하&하하", "UTF-8");
//		backParam.putParam("backParam3", "한글을 보낸다.");
//		
//    	// Animation Type
//		// DEFALUT : 왼쪽으로 이동되는 슬라이드 효과(SLIDE_LEFT)
//        // NONE : 애니메이션 효과 없음
//        // SLIDE_LEFT : 왼쪽으로 이동되는 슬라이드 효과
//        // SLIDE_RIGHT : 오른쪽으로 이동되는 슬라이드 효과
//        // SLIDE_TOP : 위쪽으로 이동되는 슬라이드 효과
//        // SLIDE_BOTTOM : 아래쪽으로 이동되는 슬라이드 효과
//        // ZOOM_IN : 줌인 효과
//        // ZOOM_OUT : 줌아웃 효과
//        // FADE : 페이드 효과
//        // MODAL_UP : 원본 화면은 고정되어 있고 대상 화면만 위쪽으로 이동되는 슬라이드 효과
//        // MODAL_DOWN : 원본 화면은 고정되어 있고 대상 화면만 아래쪽으로 이동되는 슬라이드 효과
//		Controller.getInstance().actionHistoryBack(backParam, "DEFAULT");
		
	}

	/**
	 * 화면이 복원될때 호출된다.
	 */
	@Override
	public void onRestoreActivity(Parameters params) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 화면 캡쳐가 완료 되었을 경우 호출된다.
	 * 웹 페이지 로딩시 발생하는 흰색 배경 처리를 위해 현재 화면 캡쳐가 완료된 후 다음 처리를 진행한다.   
	 * cf) 반드시 setViewForCapture(view) 메서드가 먼저 호출되어야 한다.
	 */
	@Override
	public void onFinishedCaptureView() {
		
		// 파라메터 설정
		Parameters pubParams = new Parameters();
		
		// 파라메터 정보를 보내는 경우 
		Parameters inParam = new Parameters();
		inParam.putParam("param1", "paramValue1");
		inParam.putParam("param2", "테&스트", "UTF-8"); 
		pubParams.putParam("PARAMETERS", inParam.getParamString());
		
		// 웹 컨테이너에서 보여질 html 페이지
		pubParams.putParam("TARGET_URL", CommonLibHandler.getInstance().g_strHTMLDirForWeb + "intro.html");	

		// 화면 방향 설정 
		// DEFAULT : 단말기에서 기본적으로 제공되는 방향 설정 적용
	    // PORT : 세로 고정
	    // LAND : 가로 고정
	    // ALL : 가로, 세로 방향 변경 가능
		pubParams.putParam("ORIENT_TYPE", "PORT");
		
		// Action type
		// NEW_SCR : 대상이 되는 화면을 화면 관리 스택에 새롭게 추가한다.
        // NO_HISTORY : 대상이 되는 화면을 화면 관리 스택에 추가하지 않는다.
        // CLEAR_TOP : 대상이 되는 이전 화면으로 이동한다. 이동시 현재 화면에서 대상 화면 사이의 모든 화면들은 제거된다.
    	int actionType = CommonLibUtil.getActionType("NEW_SCR");
    	
    	// Animation Type
    	// DEFALUT : 왼쪽으로 이동되는 슬라이드 효과(SLIDE_LEFT)
        // NONE : 애니메이션 효과 없음
        // SLIDE_LEFT : 왼쪽으로 이동되는 슬라이드 효과
        // SLIDE_RIGHT : 오른쪽으로 이동되는 슬라이드 효과
        // SLIDE_TOP : 위쪽으로 이동되는 슬라이드 효과
        // SLIDE_BOTTOM : 아래쪽으로 이동되는 슬라이드 효과
        // ZOOM_IN : 줌인 효과
        // ZOOM_OUT : 줌아웃 효과
        // FADE : 페이드 효과
        // MODAL_UP : 원본 화면은 고정되어 있고 대상 화면만 위쪽으로 이동되는 슬라이드 효과
        // MODAL_DOWN : 원본 화면은 고정되어 있고 대상 화면만 아래쪽으로 이동되는 슬라이드 효과
    	
    	// 하이브리드 웹화면으로 이동시에는 타겟 클래스 인텍스가 반드시 LibDefinitions.libactivities.ACTY_MAIN 이어야 한다. 
    	Controller.getInstance().actionMoveActivity(LibDefinitions.libactivities.ACTY_MAIN, 
    												actionType, 
    												thisObj, 
    												"NONE",
    												pubParams);
	}

	@Override
	public void onApplicationWillTerminate() {
		// TODO Auto-generated method stub
		
	}
}
