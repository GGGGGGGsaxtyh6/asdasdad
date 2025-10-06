function movInit()
{
   AlertResponse = "layout";
   english_usStrings = new Object();
   english_usStrings.titles = new Array("Privacy","Local Storage","Microphone","Camera");
   english_usStrings.storageLevels = new Array({label:"Never",value:-1},{label:"None",value:0},{label:"10 KB",value:10},{label:"100 KB",value:100},{label:"1 MB",value:1024},{label:"10 MB",value:10240},{label:"Unlimited",value:-2});
   english_usStrings.available = new Array(0,10,100,1024,10240,-2);
   english_usStrings.displayAs = new Array("zero KB","10 KB","100 KB","1 MB","10 MB","unlimited");
   english_usStrings.windowTitle = "Macromedia Flash Player Settings";
   english_usStrings.Close = "Close";
   english_usStrings.Yes = "Yes";
   english_usStrings.No = "No";
   english_usStrings.Allow = "Allow";
   english_usStrings.OK = "OK";
   english_usStrings.Cancel = "Cancel";
   english_usStrings.Deny = "Deny";
   english_usStrings.Retry = "Retry";
   english_usStrings.Never = "Never";
   english_usStrings.Remember = "Remember";
   english_usStrings.Volume = "Record Volume:";
   english_usStrings.Echo = "Reduce Echo";
   english_usStrings.privacyMsgText = "Allow %1 to access your camera and microphone?";
   english_usStrings.StorageMsgText = "How much information can %1 store on your computer?";
   english_usStrings.popUp_StorageText = "%1 is requesting permission to store information on your computer.";
   english_usStrings.popUp_StorageData = "Requested: up to %2<br>Currently Used: %3";
   english_usStrings.storageAlert = "Decreasing the amount below %2 will cause all information for %1 to be removed.";
   english_usStrings.Current = "Currently used:";
   english_usStrings.waitMsg = "Detecting cameras...";
   english_usStrings.noCam = "There is no camera found on your system";
   english_usStrings.storageError = "Not all stored data could be removed.";
   english_usStrings.local = "local";
   english_usStrings.installerTitle = "Macromedia Flash Central";
   english_usStrings.installMsg_confirm = "To install this service you must install Macromedia Flash Central. Would you like to install it now?";
   english_usStrings.installMsg_error = "Unable to install Macromedia Flash Central.  Would you like to retry?";
   english_usStrings.installMsg_progress = "Downloading install package...";
   english_usStrings.DP_error_1 = inputState.name;
   english_usStrings.DP_error_2 = "A download error occured. Try to download again?";
   english_usStrings.DP_error_3 = "Couldn\'t write the application to the hard disk. Please verify the hard disk is available and try again.";
   germanStrings = new Object();
   germanStrings.titles = new Array("Zugriffsschutz","Lokaler Speicher","Mikrofon","Kamera");
   germanStrings.storageLevels = new Array({label:"Nie",value:-1},{label:"Keine",value:0},{label:"10 KB",value:10},{label:"100 KB",value:100},{label:"1 MB",value:1024},{label:"10 MB",value:10240},{label:"Unbegrenzt",value:-2});
   germanStrings.available = new Array(0,10,100,1024,10240,-2);
   germanStrings.displayAs = new Array("0 KB","10 KB","100 KB","1 MB","10 MB","unbegrenzt");
   germanStrings.windowTitle = "Einstellungen für Macromedia Flash Player";
   germanStrings.Close = "Schließen";
   germanStrings.Yes = "Ja";
   germanStrings.No = "Nein";
   germanStrings.Allow = "Zulassen";
   germanStrings.OK = "OK";
   germanStrings.Cancel = "Abbrechen";
   germanStrings.Deny = "Verweigern";
   germanStrings.Retry = "Wiederholen";
   germanStrings.Never = "Nie";
   germanStrings.Remember = "Speichern";
   germanStrings.Volume = "Aufnahmelautstärke:";
   germanStrings.Echo = "Echo reduzieren";
   germanStrings.privacyMsgText = "%1 den Zugriff auf Ihre Kamera und Ihr Mikrofon gestatten?";
   germanStrings.StorageMsgText = "Wie viele Informationen darf %1 auf Ihrem Computer speichern?";
   germanStrings.popUp_StorageText = "%1 bittet um Genehmigung zur Speicherung von Informationen auf Ihrem Computer.";
   germanStrings.popUp_StorageData = "Angefordert: bis zu %2<br>Gegenwärtig verwendet: %3";
   germanStrings.storageAlert = "Wenn Sie den Wert so weit reduzieren, dass er unter %2 liegt, werden alle Informationen für %1 entfernt.";
   germanStrings.Current = "Gegenwärtig verwendet:";
   germanStrings.waitMsg = "Kameras werden erfasst...";
   germanStrings.storageError = "Nicht alle gespeicherten Daten konnten entfernt werden.";
   germanStrings.local = "Lokal";
   germanStrings.installerTitle = "Macromedia Flash Central";
   germanStrings.installMsg_confirm = "Zur Installation dieses Dienstes ist Macromedia Flash Central erforderlich. Möchten Sie Macromedia Flash Central jetzt installieren?";
   germanStrings.installMsg_error = "Macromedia Flash Central kann nicht installiert werden. Möchten Sie es noch einmal versuchen?";
   germanStrings.installMsg_progress = "Installationspaket wird heruntergeladen...";
   germanStrings.DP_error_1 = inputState.name;
   germanStrings.DP_error_2 = "Downloadfehler aufgetreten. Bitte versuchen Sie es erneut.";
   germanStrings.DP_error_3 = "Die Anwendung konnte nicht auf die Festplatte geschrieben werden. Bitte überprüfen Sie die verfügbare Festplatte und versuchen Sie es noch einmal.";
   portugueseStrings = new Object();
   portugueseStrings.titles = new Array("Privacidade","Armazenamento local","Microfone","Câmera");
   portugueseStrings.storageLevels = new Array({label:"Nunca",value:-1},{label:"Nenhum",value:0},{label:"10 KB",value:10},{label:"100 KB",value:100},{label:"1 MB",value:1024},{label:"10 MB",value:10240},{label:"Ilimitado",value:-2});
   portugueseStrings.available = new Array(0,10,100,1024,10240,-2);
   portugueseStrings.displayAs = new Array("Zero KB","10 KB","100 KB","1 MB","10 MB","ilimitado");
   portugueseStrings.windowTitle = "Configurações do Macromedia Flash Player";
   portugueseStrings.Close = "Fechar";
   portugueseStrings.Yes = "Sim";
   portugueseStrings.No = "Não";
   portugueseStrings.Allow = "Permitir";
   portugueseStrings.OK = "OK";
   portugueseStrings.Cancel = "Cancelar";
   portugueseStrings.Deny = "Negar";
   portugueseStrings.Retry = "Novamente";
   portugueseStrings.Never = "Nunca";
   portugueseStrings.Remember = "Lembrar";
   portugueseStrings.Volume = "Volume do registro:";
   portugueseStrings.Echo = "Reduzir eco";
   portugueseStrings.privacyMsgText = "Permitir que %1 acesse sua câmera e microfone?";
   portugueseStrings.StorageMsgText = "Que volume de informações %1 pode armazenar no seu computador?";
   portugueseStrings.popUp_StorageText = "%1 está pedindo permissão para armazenar informações no seu computador.";
   portugueseStrings.popUp_StorageData = "Solicitado: até %2<br>Atualmente usado: 3%";
   portugueseStrings.storageAlert = "Diminuir o valor para menos de %2 fará com que todas as informações para %1 sejam removidas.";
   portugueseStrings.Current = "Atualmente usado:";
   portugueseStrings.waitMsg = "Detectando câmeras...";
   portugueseStrings.storageError = "Não foi possível remover todos os dados.";
   portugueseStrings.local = "local";
   portugueseStrings.installerTitle = "Macromedia Flash Central";
   portugueseStrings.installMsg_confirm = "Para instalar este serviço, o Macromedia Flash Central deve estar instalado. Deseja fazê-lo agora?";
   portugueseStrings.installMsg_error = "Não foi possível instalar o Macromedia Flash Central. Deseja tentar novamente?";
   portugueseStrings.installMsg_progress = "Fazendo download do pacote de instalação...";
   portugueseStrings.DP_error_1 = inputState.name;
   portugueseStrings.DP_error_2 = "Ocorreu um erro de download. Deseja tentar fazer o download novamente ?";
   portugueseStrings.DP_error_3 = "Não foi possivel gravar o aplicativo no disco rgido. Verifique a disponibilidade do disco e tente outra vez.";
   spanishStrings = new Object();
   spanishStrings.titles = new Array("Privacidad","Almacenamiento local","Micrófono","Cámara");
   spanishStrings.storageLevels = new Array({label:"Nunca",value:-1},{label:"Ninguno",value:0},{label:"10 KB",value:10},{label:"100 KB",value:100},{label:"1 MB",value:1024},{label:"10 MB",value:10240},{label:"Ilimitado",value:-2});
   spanishStrings.available = new Array(0,10,100,1024,10240,-2);
   spanishStrings.displayAs = new Array("0 KB","10 KB","100 KB","1 MB","10 MB","ilimitado");
   spanishStrings.windowTitle = "Configuración de Macromedia Flash Player";
   spanishStrings.Close = "Cerrar";
   spanishStrings.Yes = "Sí";
   spanishStrings.No = "No";
   spanishStrings.Allow = "Permitir";
   spanishStrings.OK = "Aceptar";
   spanishStrings.Cancel = "Cancelar";
   spanishStrings.Deny = "Denegar";
   spanishStrings.Retry = "Reintentar";
   spanishStrings.Never = "Nunca";
   spanishStrings.Remember = "Recordar";
   spanishStrings.Volume = "Volumen de registro:";
   spanishStrings.Echo = "Reducir eco";
   spanishStrings.privacyMsgText = "¿Desea que %1 pueda acceder a la cámara y al micrófono?";
   spanishStrings.StorageMsgText = "¿Cuánta información puede almacenar %1 en su PC?";
   spanishStrings.popUp_StorageText = "%1 solicita autorización para almacenar información en su PC.";
   spanishStrings.popUp_StorageData = "Solicitado: hasta %2<br>Actualmente usado: %3";
   spanishStrings.storageAlert = "Si disminuye a menos de %2 se quitará toda la información para %1.";
   spanishStrings.Current = "Actualmente usado:";
   spanishStrings.waitMsg = "Detectando cámaras...";
   spanishStrings.storageError = "No se pudieron quitar todos los datos almacenados.";
   spanishStrings.local = "local";
   spanishStrings.installerTitle = "Macromedia Flash Central";
   spanishStrings.installMsg_confirm = "Para instalar este servicio, primero debe instalar Macromedia Flash Central. ¿Desea instalarlo ahora?";
   spanishStrings.installMsg_error = "No se pudo instalar Macromedia Flash Central. ¿Desea intentarlo de nuevo?";
   spanishStrings.installMsg_progress = "Descargando paquete de instalación...";
   spanishStrings.DP_error_1 = inputState.name;
   spanishStrings.DP_error_2 = "Error al descargar. Desea volver a intentarlo?";
   spanishStrings.DP_error_3 = "No se pudo grabar la aplicacin en el disco duro. Compruebe el disco duro disponible y vuelva a intentarlo.";
   italianStrings = new Object();
   italianStrings.titles = new Array("Privacy","Archiviazione locale","Microfono","Videocamera");
   italianStrings.storageLevels = new Array({label:"Mai",value:-1},{label:"Nessun",value:0},{label:"10 KB",value:10},{label:"100 KB",value:100},{label:"1 MB",value:1024},{label:"10 MB",value:10240},{label:"Illimitati",value:-2});
   italianStrings.available = new Array(0,10,100,1024,10240,-2);
   italianStrings.displayAs = new Array("zero KB","10 KB","100 KB","1 MB","10 MB","illimitata");
   italianStrings.windowTitle = "Impostazioni di Macromedia Flash Player";
   italianStrings.Close = "Chiudi";
   italianStrings.Yes = "Sì";
   italianStrings.No = "No";
   italianStrings.Allow = "Consenti";
   italianStrings.OK = "OK";
   italianStrings.Cancel = "Annulla";
   italianStrings.Deny = "Nega";
   italianStrings.Retry = "Riprova";
   italianStrings.Never = "Mai";
   italianStrings.Remember = "Ricorda";
   italianStrings.Volume = "Volume di registrazione";
   italianStrings.Echo = "Riduci eco";
   italianStrings.privacyMsgText = "Consentire a %1 di accedere alla videocamera e al microfono in uso?";
   italianStrings.StorageMsgText = "Quanti dati può archiviare %1 sul computer in uso?";
   italianStrings.popUp_StorageText = "%1 sta chiedendo il permesso di archiviare informazioni sul computer in uso.";
   italianStrings.popUp_StorageData = "Richiesti: fino a  %2<br>Attualmente in uso: %3";
   italianStrings.storageAlert = "Riducendo questa cifra al di sotto di %2 tutte le informazioni relative a %1 verranno eliminate.";
   italianStrings.Current = "Attualmente in uso:";
   italianStrings.waitMsg = "Rilevamento videocamere in corso...";
   italianStrings.storageError = "Non è stato possibile eliminare tutti i dati archiviati.";
   italianStrings.local = "locale";
   italianStrings.installerTitle = "Installazione di Macromedia Flash Central";
   italianStrings.installMsg_confirm = "Per installare questo servizio, è necessario installare Macromedia Flash Central. Installarlo ora?";
   italianStrings.installMsg_error = "Impossibile installare Macromedia Flash Central. Ritentare?";
   italianStrings.installMsg_progress = "Scaricamento del pacchetto di installazione in corso...";
   italianStrings.DP_error_1 = inputState.name;
   italianStrings.DP_error_2 = "Si  è verificato un errore di scaricamento. Scaricare di nuovo?";
   italianStrings.DP_error_3 = "Impossibile scrivere l\'applicazione sul disco rigido. Controllare se il disco rigido è disponibile e ritentare";
   koreanStrings = new Object();
   koreanStrings.titles = new Array("개인 정보","로컬 저장소","마이크","카메라");
   koreanStrings.storageLevels = new Array({label:"안함",value:-1},{label:"없음",value:0},{label:"10 KB",value:10},{label:"100 KB",value:100},{label:"1 MB",value:1024},{label:"10 MB",value:10240},{label:"제한 없음",value:-2});
   koreanStrings.available = new Array(0,10,100,1024,10240,-2);
   koreanStrings.displayAs = new Array("0 KB","10 KB","100 KB","1 MB","10 MB","무제한");
   koreanStrings.windowTitle = "Macromedia Flash Player 설정";
   koreanStrings.Close = "닫기";
   koreanStrings.Yes = "예";
   koreanStrings.No = "아니오";
   koreanStrings.Allow = "허용";
   koreanStrings.OK = "확인";
   koreanStrings.Cancel = "취소";
   koreanStrings.Deny = "거부";
   koreanStrings.Retry = "재시도";
   koreanStrings.Never = "안함";
   koreanStrings.Remember = "기억";
   koreanStrings.Volume = "녹음 볼륨:";
   koreanStrings.Echo = "반향 줄이기";
   koreanStrings.privacyMsgText = "카메라와 마이크를 사용하기 위해 %1을(를) 허용하시겠습니까?";
   koreanStrings.StorageMsgText = "컴퓨터에 %1 저장할 수 있는 정보가 얼마나 됩니까?";
   koreanStrings.popUp_StorageText = "%1이(가) 컴퓨터에 정보를 저장하기 위한 승인을 요구하고 있습니다.";
   koreanStrings.popUp_StorageData = "요구량: 최대 %2<br>현재 사용량: %3";
   koreanStrings.storageAlert = "사용량이 %2 이하로 감소하면 %1에 대한 정보가 모두 제거됩니다.";
   koreanStrings.Current = "현재 사용량:";
   koreanStrings.waitMsg = "카메라 검색 중…";
   koreanStrings.storageError = "저장된 데이터가 모두 제거되지 않을 수 있습니다.";
   koreanStrings.local = "로컬";
   koreanStrings.installerTitle = "Macromedia Flash Central";
   koreanStrings.installMsg_confirm = "이 서비스를 설치하려면 Macromedia Flash Central을 설치해야 합니다. 지금 설치하시겠습니까?";
   koreanStrings.installMsg_error = "Macromedia Flash Central을 설치할 수 없습니다. 다시 하시겠습니까?";
   koreanStrings.installMsg_progress = "설치 패키지 다운로드 중…";
   koreanStrings.DP_error_1 = inputState.name;
   koreanStrings.DP_error_2 = "다운로드 에러가 발생했습니다. 다시 다운로드 하시겠습니까?";
   koreanStrings.DP_error_3 = "애플리케이션을 하드 디스크에 쓰지 못합니다. 하드 디스크가 사용 가능한지 확인하시고 다시 시도해 보십시오.";
   japaneseStrings = new Object();
   japaneseStrings.titles = new Array("プライバシー","ローカル記憶領域","マイク","カメラ");
   japaneseStrings.storageLevels = new Array({label:"禁止",value:-1},{label:"なし",value:0},{label:"10 KB",value:10},{label:"100 KB",value:100},{label:"1 MB",value:1024},{label:"10 MB",value:10240},{label:"制限しない",value:-2});
   japaneseStrings.available = new Array(0,10,100,1024,10240,-2);
   japaneseStrings.displayAs = new Array("0 KB","10 KB","100 KB","1 MB","10 MB","制限しない");
   japaneseStrings.windowTitle = "Macromedia Flash Player 設定";
   japaneseStrings.Close = "閉じる";
   japaneseStrings.Yes = "はい";
   japaneseStrings.No = "いいえ";
   japaneseStrings.Allow = "許可";
   japaneseStrings.OK = "OK";
   japaneseStrings.Cancel = "キャンセル";
   japaneseStrings.Deny = "拒否";
   japaneseStrings.Retry = "再試行";
   japaneseStrings.Never = "今後表示しない";
   japaneseStrings.Remember = "後で確認";
   japaneseStrings.Volume = "録音ボリューム :";
   japaneseStrings.Echo = "エコーを減らす";
   japaneseStrings.privacyMsgText = "%1 のカメラおよびマイクへのアクセスを許可しますか ?";
   japaneseStrings.StorageMsgText = "このコンピュータで保存するインフォメーション容量";
   japaneseStrings.popUp_StorageText = "%1 はコンピュータに情報を保存する許可を要求しています。";
   japaneseStrings.popUp_StorageData = "リクエスト容量: %2<br>使用中の容量: %3";
   japaneseStrings.storageAlert = "%2 以下にすると、%1 のすべての情報が削除される可能性があります。";
   japaneseStrings.Current = "現在使用中 :";
   japaneseStrings.waitMsg = "カメラを検知しています...";
   japaneseStrings.storageError = "一部のデータを削除できませんでした。";
   japaneseStrings.local = "ローカル";
   japaneseStrings.installerTitle = "Macromedia Flash Central";
   japaneseStrings.installMsg_confirm = "このサービスをインストールするには、Macromedia Flash Central をインストールする必要があります。Macromedia Flash Central をインストールしますか ?";
   japaneseStrings.installMsg_error = "Macromedia Flash Central をインストールできません。再試行しますか ?";
   japaneseStrings.installMsg_progress = "インストールパッケージをダウンロードしています...";
   japaneseStrings.DP_error_2 = "ダウンロードエラーが起こりました。ダウンロードを再び行いますか？";
   japaneseStrings.DP_error_3 = "アプリケーションをハードディスクに書き込めませんでした。ハードディスクに空き容量があるか確認し、もう一度お試しください。";
   CHSStrings = new Object();
   CHSStrings.titles = new Array("保密性","本地存储","麦克风","照相机");
   CHSStrings.storageLevels = new Array({label:"从不",value:-1},{label:"无",value:0},{label:"10 KB",value:10},{label:"100 KB",value:100},{label:"1 MB",value:1024},{label:"10 MB",value:10240},{label:"无限制",value:-2});
   CHSStrings.available = new Array(0,10,100,1024,10240,-2);
   CHSStrings.displayAs = new Array("0 KB","10 KB","100 KB","1 MB","10 MB","无限制");
   CHSStrings.windowTitle = "Macromedia Flash Player 设置";
   CHSStrings.Close = "关闭";
   CHSStrings.Yes = "是";
   CHSStrings.No = "否";
   CHSStrings.Allow = "允许";
   CHSStrings.OK = "确定";
   CHSStrings.Cancel = "取消";
   CHSStrings.Deny = "拒绝";
   CHSStrings.Retry = "重试";
   CHSStrings.Never = "从不";
   CHSStrings.Remember = "记住";
   CHSStrings.Volume = "记录量:";
   CHSStrings.Echo = "降低回音";
   CHSStrings.privacyMsgText = "是否允许 %1 使用您的照相机和麦克风？";
   CHSStrings.StorageMsgText = "%1 可在您的计算机上存储多少信息？";
   CHSStrings.popUp_StorageText = "%1 请求在您的计算机上存储信息。";
   CHSStrings.popUp_StorageData = "请求：最多为 %2<br>当前已使用：%3";
   CHSStrings.storageAlert = "将数量减小到 %2 以下会导致删除 %1 的信息。";
   CHSStrings.Current = "当前已使用:";
   CHSStrings.waitMsg = "正在检测照相机...";
   CHSStrings.storageError = "并非所有的存储数据都可删除。";
   CHSStrings.local = "本地";
   CHSStrings.installerTitle = "Macromedia Flash Central";
   CHSStrings.installMsg_confirm = "必须安装 Macromedia Flash Central 才能安装此服务。是否要立即安装 Macromedia Flash Central？";
   CHSStrings.installMsg_error = "无法安装 Macromedia Flash Central。是否重试？";
   CHSStrings.installMsg_progress = "正在下载安装软件包...";
   CHSStrings.DP_error_1 = inputState.name;
   CHSStrings.installMsg_error_2 = "下载出错。是否尝试再次下载？";
   CHSStrings.installMsg_error_3 = "无法将应用程序写入硬盘，请检查硬盘并再试一次。";
   CHTStrings = new Object();
   CHTStrings.titles = new Array("私用","本地儲存區","麥克風","相機");
   CHTStrings.storageLevels = new Array({label:"永不",value:-1},{label:"無",value:0},{label:"10 KB",value:10},{label:"100 KB",value:100},{label:"1 MB",value:1024},{label:"10 MB",value:10240},{label:"無限制",value:-2});
   CHTStrings.available = new Array(0,10,100,1024,10240,-2);
   CHTStrings.displayAs = new Array("0 KB","10 KB","100 KB","1 MB","10 MB","無限制");
   CHTStrings.windowTitle = "Macromedia Flash Player 設定";
   CHTStrings.Close = "關閉";
   CHTStrings.Yes = "是";
   CHTStrings.No = "否";
   CHTStrings.Allow = "允許";
   CHTStrings.OK = "確定";
   CHTStrings.Cancel = "取消";
   CHTStrings.Deny = "拒絕";
   CHTStrings.Retry = "重試";
   CHTStrings.Never = "永不";
   CHTStrings.Remember = "記憶";
   CHTStrings.Volume = "錄音音量:";
   CHTStrings.Echo = "降低回音";
   CHTStrings.privacyMsgText = "是否允許 %1 存取您的相機和麥克風？";
   CHTStrings.StorageMsgText = "%1 能在您的電腦上儲存多少資訊？";
   CHTStrings.popUp_StorageText = "%1 要求能在您電腦上儲存資訊的權限。";
   CHTStrings.popUp_StorageData = "要求量:最多 %2<br>目前使用量: %3";
   CHTStrings.storageAlert = "將數量降至 %2 以下，將會移除 %1 所有的資訊。";
   CHTStrings.Current = "目前使用量:";
   CHTStrings.waitMsg = "正在偵測相機...";
   CHTStrings.storageError = "無法移除所有儲存的資料。";
   CHTStrings.local = "本地";
   CHTStrings.installerTitle = "Macromedia Flash Central ";
   CHTStrings.installMsg_confirm = "要安裝這項服務，您必須先安裝 Macromedia Flash Central。是否要現在安裝？";
   CHTStrings.installMsg_error = "無法安裝 Macromedia Flash Central。您要重試嗎？";
   CHTStrings.installMsg_progress = "下載安裝程式...";
   CHTStrings.DP_error_1 = inputState.name;
   CHTStrings.DP_error_2 = "發生下載錯誤, 重新下載? ";
   CHTStrings.DP_error_3 = "無法將應用程式寫入到硬碟, 請檢查硬碟並再試一次。";
   frenchStrings = new Object();
   frenchStrings.titles = new Array("Contrôle de l’accès","Enregistrement local","Microphone","Caméra");
   frenchStrings.storageLevels = new Array({label:"Jamais",value:-1},{label:"Aucun",value:0},{label:"10 Ko",value:10},{label:"100 Ko",value:100},{label:"1 Mo",value:1024},{label:"10 Mo",value:10240},{label:"Illimité",value:-2});
   frenchStrings.available = new Array(0,10,100,1024,10240,-2);
   frenchStrings.displayAs = new Array("0 Ko","10 Ko","100 Ko","1 Mo","10 Mo","illimité");
   frenchStrings.windowTitle = "Paramètres Macromedia Flash Player";
   frenchStrings.Close = "Fermer";
   frenchStrings.Yes = "Oui";
   frenchStrings.No = "Non";
   frenchStrings.Allow = "Autoriser";
   frenchStrings.OK = "OK";
   frenchStrings.Cancel = "Annuler";
   frenchStrings.Deny = "Refuser";
   frenchStrings.Retry = "Réessayer";
   frenchStrings.Never = "Jamais";
   frenchStrings.Remember = "Mémoriser";
   frenchStrings.Volume = "Volume d’enregistrement :";
   frenchStrings.Echo = "Réduire l’écho";
   frenchStrings.privacyMsgText = "Autoriser %1 à accéder à votre caméra et à votre microphone ?";
   frenchStrings.StorageMsgText = "Quelle est la quantité d’informations que %1 peut enregistrer sur votre ordinateur ?";
   frenchStrings.popUp_StorageText = "%1 demande la permission d’enregistrer des informations sur votre ordinateur.";
   frenchStrings.popUp_StorageData = "Demandé : jusqu’à %2<br>Actuellement utilisé : %3";
   frenchStrings.storageAlert = "Un niveau inférieur à %2 entraînera la suppression de toutes les informations pour %1.";
   frenchStrings.Current = "Actuellement utilisé :";
   frenchStrings.waitMsg = "Détection des caméras...";
   frenchStrings.storageError = "Toutes les données enregistrées n’ont pas pu être supprimées.";
   frenchStrings.local = "local(e)";
   frenchStrings.installerTitle = "Macromedia Flash Central";
   frenchStrings.installMsg_confirm = "L’installation de ce service requiert l’installation de Macromedia Flash Central. Souhaitez-vous procéder à son installation ?";
   frenchStrings.installMsg_error = "Impossible d’installer Macromedia Flash Central. Voulez-vous réessayer ?";
   frenchStrings.installMsg_progress = "Téléchargement du paquetage d’installation...";
   frenchStrings.DP_error_1 = inputState.name;
   frenchStrings.DP_error_2 = "Une erreur de téléchargement s\'est produite. Souhaitez-vous réessayer ?";
   frenchStrings.DP_error_3 = "Impossible d’écrire l’application sur le disque dur. Vérifiez l’espace disque disponible avant de réessayer.";
   swedishStrings = new Object();
   swedishStrings.titles = new Array("Säkerhet","Lokal lagring","Mikrofon","Kamera");
   swedishStrings.storageLevels = new Array({label:"Aldrig",value:-1},{label:"Ingen",value:0},{label:"10 kB",value:10},{label:"100 kB",value:100},{label:"1 MB",value:1024},{label:"10 MB",value:10240},{label:"Obegränsat",value:-2});
   swedishStrings.available = new Array(0,10,100,1024,10240,-2);
   swedishStrings.displayAs = new Array("0 kB","10 kB","100 kB","1 MB","10 MB","obegränsat");
   swedishStrings.windowTitle = "Macromedia Flash Player-inställningar";
   swedishStrings.Close = "Stäng";
   swedishStrings.Yes = "Ja";
   swedishStrings.No = "Nej";
   swedishStrings.Allow = "Tillåt";
   swedishStrings.OK = "OK";
   swedishStrings.Cancel = "Avbryt";
   swedishStrings.Deny = "Neka";
   swedishStrings.Retry = "Försök igen";
   swedishStrings.Never = "Aldrig";
   swedishStrings.Remember = "Kom ihåg";
   swedishStrings.Volume = "Inspelningsvolym:";
   swedishStrings.Echo = "Minska eko";
   swedishStrings.privacyMsgText = "Vill du tillåta att %1 får åtkomst till din kamera och mikrofon?";
   swedishStrings.StorageMsgText = "Hur mycket information vill du tillåta att %1 lagrar på datorn?";
   swedishStrings.popUp_StorageText = "%1 har begärt att få lagra information på datorn.";
   swedishStrings.popUp_StorageData = "Begärt: upp till %2<br>Används: %3";
   swedishStrings.storageAlert = "Om du minskar mängden under %2 kommer all information för %1 att tas bort.";
   swedishStrings.Current = "Används:";
   swedishStrings.waitMsg = "Identifierar kameror...";
   swedishStrings.storageError = "Det gick inte att ta bort alla lagrade data.";
   swedishStrings.local = "lokalt";
   swedishStrings.installerTitle = "Macromedia Flash Central";
   swedishStrings.installMsg_confirm = "Om du vill installera den här tjänsten måste du installera Macromedia Flash Central. Vill du installera det nu?";
   swedishStrings.installMsg_error = "Det gick inte att installera Macromedia Flash Central. Vill du försöka igen?";
   swedishStrings.installMsg_progress = "Hämtar installationspaket...";
   swedishStrings.DP_error_1 = inputState.name;
   swedishStrings.DP_error_2 = "Det uppstod ett hämtningsfel. Vill du försöka hämta igen?";
   swedishStrings.DP_error_3 = "Det gick inte att skriva programmet till hårddisken. Kontrollera att hårddisken är tillgänglig och försök sedan igen.";
   germanStrings = new Object();
   germanStrings.titles = new Array("Zugriffsschutz","Lokaler Speicher","Mikrofon","Kamera");
   germanStrings.storageLevels = new Array({label:"Nie",value:-1},{label:"Keine",value:0},{label:"10 KB",value:10},{label:"100 KB",value:100},{label:"1 MB",value:1024},{label:"10 MB",value:10240},{label:"Unbegrenzt",value:-2});
   germanStrings.available = new Array(0,10,100,1024,10240,-2);
   germanStrings.displayAs = new Array("0 KB","10 KB","100 KB","1 MB","10 MB","unbegrenzt");
   germanStrings.windowTitle = "Einstellungen für Macromedia Flash Player";
   germanStrings.Close = "Schließen";
   germanStrings.Yes = "Ja";
   germanStrings.No = "Nein";
   germanStrings.Allow = "Zulassen";
   germanStrings.OK = "OK";
   germanStrings.Cancel = "Abbrechen";
   germanStrings.Deny = "Verweigern";
   germanStrings.Retry = "Wiederholen";
   germanStrings.Never = "Nie";
   germanStrings.Remember = "Speichern";
   germanStrings.Volume = "Aufnahmelautstärke:";
   germanStrings.Echo = "Echo reduzieren";
   germanStrings.privacyMsgText = "%1 den Zugriff auf Ihre Kamera und Ihr Mikrofon gestatten?";
   germanStrings.StorageMsgText = "Wie viele Informationen darf %1 auf Ihrem Computer speichern?";
   germanStrings.popUp_StorageText = "%1 bittet um Genehmigung zur Speicherung von Informationen auf Ihrem Computer.";
   germanStrings.popUp_StorageData = "Angefordert: bis zu %2<br>Gegenwärtig verwendet: %3";
   germanStrings.storageAlert = "Wenn Sie den Wert so weit reduzieren, dass er unter %2 liegt, werden alle Informationen für %1 entfernt.";
   germanStrings.Current = "Gegenwärtig verwendet:";
   germanStrings.waitMsg = "Kameras werden erfasst...";
   germanStrings.storageError = "Nicht alle gespeicherten Daten konnten entfernt werden.";
   germanStrings.local = "Lokal";
   germanStrings.installerTitle = "Macromedia Flash Central";
   germanStrings.installMsg_confirm = "Zur Installation dieses Dienstes ist Macromedia Flash Central erforderlich. Möchten Sie Macromedia Flash Central jetzt installieren?";
   germanStrings.installMsg_error = "Macromedia Flash Central kann nicht installiert werden. Möchten Sie es noch einmal versuchen?";
   germanStrings.installMsg_progress = "Installationspaket wird heruntergeladen...";
   germanStrings.DP_error_1 = inputState.name;
   germanStrings.DP_error_2 = "Downloadfehler aufgetreten. Bitte versuchen Sie es erneut.";
   germanStrings.DP_error_3 = "Die Anwendung konnte nicht auf die Festplatte geschrieben werden. Bitte überprüfen Sie die verfügbare Festplatte und versuchen Sie es noch einmal.";
   var lang = System.capabilities.language;
   kb = "KB";
   switch(lang)
   {
      case "en-US":
         language = "english_us";
         myStyle = "defStyle";
         break;
      case "de":
         language = "german";
         myStyle = "defStyle";
         break;
      case "fr":
         kb = "Ko";
         language = "french";
         myStyle = "defStyle";
         break;
      case "sv":
         language = "swedish";
         smyStyle = "altStyle";
         break;
      case "zh-TW":
         language = "CHT";
         myStyle = "altStyle";
         break;
      case "zh-CN":
         language = "CHS";
         myStyle = "altStyle";
         break;
      case "ja":
         language = "japanese";
         myStyle = "altStyle";
         break;
      case "ko":
         language = "korean";
         myStyle = "altStyle";
         break;
      case "it":
         language = "italian";
         myStyle = "defStyle";
         break;
      case "es":
         language = "spanish";
         myStyle = "defStyle";
         break;
      case "pt":
         language = "portuguese";
         myStyle = "defStyle";
         break;
      default:
         language = "english_us";
         myStyle = "defStyle";
   }
   ls = eval(language + "Strings");
   rads = new Object();
   itabs = new Array();
   foundCams = false;
   windowTitle.text = ls.windowTitle;
   setStyle(windowTitle);
   checkPort(windowTitle);
   Close.label.text = ls.Close;
   setStyle(Close.label);
   block._width = 4000;
   block._height = 4000;
}
function setStyle(field)
{
   if(myStyle == "altStyle")
   {
      fSize = 11;
   }
   else
   {
      fSize = 10;
   }
   theStyle = new TextFormat();
   theStyle.size = fSize;
   field.setTextFormat(theStyle);
}
function checkPort(field)
{
   if(language == "portuguese")
   {
      nineStyle = new TextFormat();
      nineStyle.size = 9;
      field.setTextFormat(nineStyle);
   }
   tout("checkPort:" + field);
}
function tout(msg)
{
   out.text += msg + "\n";
}
function killHand()
{
   for(i in myRoot)
   {
      if(myRoot[i] instanceof Button || myRoot[i] instanceof MovieClip)
      {
         myRoot[i].useHandCursor = false;
      }
   }
}
function checkMode()
{
   if(inputState.Mode == 0)
   {
      gotoAndStop(2);
   }
   else if(inputState.Mode == 1)
   {
      gotoAndStop(7);
   }
   else if(inputState.Mode == 2)
   {
      gotoAndStop(8);
   }
   else if(inputState.Mode == 3)
   {
      gotoAndStop(11);
   }
}
function getAppropriateLevel(kc)
{
   var radVal = -2;
   i = 0;
   while(i < ls.storageLevels.length)
   {
      if(kc <= ls.storageLevels[i].value && kc != -2)
      {
         radVal = ls.storageLevels[i].value;
         break;
      }
      i++;
   }
   return radVal;
}
function setTitle(frame)
{
   titles.label.text = ls.titles[frame];
   setStyle(titles.label);
}
function allowStorageChange()
{
   domainSettings.data.klimit = newVal;
   AlertResponse = "klimit";
   usedBox.text = inputState.kcurrent;
   setStye(usedBox);
   if(inputState.kcurrent != 0)
   {
      gotoAndStop(10);
      t_msg.htmlText = ls.storageError;
      setStye(t_msg);
      OK.label.text = ls.OK;
      setStyle(OK.label);
   }
   else
   {
      gotoAndStop(4);
      AlertResponse = "klimit";
      newVal = null;
   }
}
function denyStorageChange()
{
   gotoAndStop(4);
   newVal = null;
}
function storageErrorOK()
{
   gotoAndStop(4);
   var appropriateLevel = getAppropriateLevel(inputState.kcurrent);
   changeKlimit(appropriateLevel);
   AlertResponse = "klimit";
}
function changeKlimit(val)
{
   needTwitch = val == -1 && inputState.kcurrent > 0 || val >= 0 && val < inputState.kcurrent;
   if(needTwitch)
   {
      newVal = val;
      gotoAndStop(9);
      var x = 0;
      while(x < ls.available.length)
      {
         if(ls.available[x] == domainSettings.data.klimit)
         {
            myKb = ls.displayAs[x];
         }
         x++;
      }
      sentence = ls.storageAlert;
      domainStyle = "<font color=\'#0066CC\'>" + inputState.domain + "</font>";
      kbStyle = myKb;
      sentence = Substitute(sentence,"%1",domainStyle);
      sentence = Substitute(sentence,"%2",kbStyle);
      t_msg.htmlText = sentence;
      setStyle(t_msg);
      OK.label.text = ls.OK;
      setStyle(OK.label);
      Cancel.label.text = ls.Cancel;
      setStyle(Cancel.label);
   }
   else
   {
      domainSettings.data.klimit = val;
      AlertResponse = "klimit";
   }
}
function pop_changeKlimit()
{
   domainSettings.data.klimit = newSetting;
   AlertResponse = "ok";
}
function doTab(obj)
{
   myRoot.gotoAndStop("_" + obj);
   iHi.gotoAndStop("i_" + obj);
}
function onChange(btn, a)
{
   if(btn == "Remember")
   {
      domainSettings.data.always = a;
      AlertResponse = "always";
      if(myRoot[btn]._currentframe == 1)
      {
         myRoot[btn].gotoAndStop(2);
      }
      else
      {
         myRoot[btn].gotoAndStop(1);
      }
   }
   else if(btn == "Echo")
   {
      playerSettings.data.echosuppression = a;
      AlertResponse = "echosuppression";
      if(myRoot[btn]._currentframe == 1)
      {
         myRoot[btn].gotoAndStop(2);
      }
      else
      {
         myRoot[btn].gotoAndStop(1);
      }
   }
}
function setPanel(x)
{
   this.playerSettings.data.panel = x;
}
function goPanel()
{
   pOffset = 3;
   tOffset = 1;
   var ps = this.playerSettings.data.panel;
   if(ps == undefined)
   {
      ps = 0;
   }
   var myFrame = ps + pOffset;
   myRoot.gotoAndPlay(myFrame);
   iHi.gotoAndStop(ps + tOffset);
}
function frame_dispatch()
{
   goPanel();
}
function frame_privacy()
{
   privacy.gotoAndStop("off");
   localInformation.gotoAndStop("on");
   microphoneSettings.gotoAndStop("on");
   cameraSettings.gotoAndStop("on");
   Allow.label.text = ls.Allow;
   setStyle(Allow.label);
   Deny.label.text = ls.Deny;
   setStyle(Deny.label);
   Remember.msg.text = ls.Remember;
   setStyle(Remember.msg);
   sentence = ls.PrivacyMsgText;
   domainStyle = "<font color=\'#0066CC\'>" + inputState.domain + "</font>";
   sentence = Substitute(sentence,"%1",domainStyle);
   t_privacy.htmlText = sentence;
   setStyle(t_privacy);
   setTitle(0);
   setPanel(0);
   cont = "privacy";
   panelName = "privacy";
   stop();
}
function frame_localInformation()
{
   privacy.gotoAndStop("on");
   localInformation.gotoAndStop("off");
   microphoneSettings.gotoAndStop("on");
   cameraSettings.gotoAndStop("on");
   Never.msg.text = ls.Never;
   setStyle(Never.msg);
   sentence = ls.StorageMsgText;
   domainStyle = "<font color=\'#0066CC\'>" + inputState.domain + "</font>";
   sentence = Substitute(sentence,"%1",domainStyle);
   t_storage.htmlText = sentence;
   setStyle(t_storage);
   Current.text = ls.Current + " " + inputState.kcurrent + kb;
   setStyle(Current);
   cont = "localinfo";
   setTitle(1);
   setPanel(1);
   Close.label.text = ls.Close;
   setStyle(Close.label);
   stop();
}
function checkSlider()
{
   if(domainSettings.data.klimit == -1)
   {
      slider.active = false;
      slider._x = _parent.crib._x;
      slider._alpha = 75;
      crib._alpha = 30;
      Never.gotoAndStop(2);
   }
   else
   {
      slider.active = true;
      Never.gotoAndStop(1);
      slider._alpha = 100;
      crib._alpha = 100;
   }
}
function toggleNever()
{
   Never.hilite.gotoAndStop(1);
   if(slider.active == false)
   {
      slider.active = true;
      Never.gotoAndStop(1);
      slider._alpha = 100;
      crib._alpha = 100;
   }
   else
   {
      slider.active = false;
      Never.gotoAndStop(2);
      changeKlimit(-1);
      localinformationSetting.text = ls.storageLevels[1].label;
      slider._x = crib._x;
      slider._alpha = 75;
      crib._alpha = 30;
   }
}
function frame_microphoneSettings()
{
   privacy.gotoAndStop("on");
   localInformation.gotoAndStop("on");
   microphoneSettings.gotoAndStop("off");
   cameraSettings.gotoAndStop("on");
   Echo.msg.text = ls.Echo;
   setStyle(Echo.msg);
   cont = "microphone";
   setTitle(2);
   setPanel(2);
   micList = Microphone.names;
   Volume.text = ls.Volume;
   setStyle(Volume);
   var foundIt = false;
   var dm = playerSettings.data.defaultMicrophone;
   micmenu.myList = new Array();
   i = 0;
   while(i < micList.length)
   {
      micmenu.myList[i] = micList[i];
      if(dm == micList[i])
      {
         myDef = dm;
         foundIt = true;
      }
      i++;
   }
   if(!foundIt)
   {
      var query = new Object();
      query.query = "defaultMicrophone";
      myRoot.AlertResponse = query;
      myDef = query.response;
   }
   myMic = Microphone["get"]();
   s = new Sound(meter);
   s.setVolume(0);
   meter.attachAudio(myMic);
   Close.label.text = ls.Close;
   setStyle(Close.label);
   stop();
}
function frame_cameraSettings()
{
   privacy.gotoAndStop("on");
   localInformation.gotoAndStop("on");
   microphoneSettings.gotoAndStop("on");
   cameraSettings.gotoAndStop("off");
   cont = "camera";
   camMsg.borderColor = 16777215;
   setTitle(3);
   setPanel(3);
   camMsg.text = ls.waitMsg;
   camList = new Array();
   camList = Camera.names;
   var foundIt = false;
   var dc = playerSettings.data.defaultCamera;
   if(camList.length == 0)
   {
      camMsg._visible = true;
      camMsg.text = ls.noCam;
      camBorder._visible = false;
      return undefined;
   }
   cammenu.myList = new Array();
   i = 0;
   while(i < camList.length)
   {
      cammenu.myList[i] = camList[i];
      if(dc == camList[i])
      {
         myDef = dc;
         foundIt = true;
         camMsg._visible = false;
      }
      i++;
   }
   if(!foundIt)
   {
      myDef = camList[0];
   }
   foundCams = true;
   camMsg._visible = false;
   myCam = Camera["get"]();
   Close.label.text = ls.Close;
   setStyle(Close.label);
   stop();
}
function showVid()
{
   myView.attachVideo(myCam);
   camBorder_butt.vidCover._visible = false;
}
function frame_pop_storage()
{
   s = 0;
   while(s < ls.available.length)
   {
      if(ls.available[s] == -2)
      {
         break;
      }
      if(inputState.krequest <= ls.available[s])
      {
         break;
      }
      s++;
   }
   setTitle(1);
   Allow.label.text = ls.Allow;
   setStyle(Allow.label);
   Deny.label.text = ls.Deny;
   setStyle(Deny.label);
   newSetting = ls.available[s];
   sentence = ls.popUp_StorageText;
   sentence2 = ls.popUp_StorageData;
   domainStyle = "<font color=\'#0066CC\'>" + inputState.domain + "</font>";
   kbStyle = ls.displayAs[s];
   currentKvalue = inputState.kcurrent + " " + kb;
   sentence = Substitute(sentence,"%1",domainStyle);
   sentence2 = Substitute(sentence2,"%2",kbStyle);
   sentence2 = Substitute(sentence2,"%3",currentKvalue);
   t_msg.htmlText = sentence;
   setStyle(t_msg);
   t_storageData.htmlText = sentence2;
   setStyle(t_storageData);
   cont = "pop_localinfo";
}
function frame_pop_privacy()
{
   setTitle(0);
   Allow.label.text = ls.Allow;
   setStyle(Allow.label);
   Deny.label.text = ls.Deny;
   setStyle(Deny.label);
   sentence = ls.PrivacyMsgText;
   domainStyle = "<font color=\'#0066CC\'>" + inputState.domain + "</font>";
   sentence = Substitute(sentence,"%1",domainStyle);
   pop_priv_msg.htmlText = sentence;
   setStyle(pop_priv_msg);
   cont = "pop_privacy";
}
function Substitute(input, token, value)
{
   var i = input.indexOf(token);
   if(i < 0)
   {
      return input;
   }
   var result = value + input.substr(i + 2);
   if(i > 0)
   {
      result = input.substr(0,i) + result;
   }
   return result;
}
function getHelpURL()
{
   base = "http://www.macromedia.com/bin/flashhelp.cgi?panel=%1&lang=%2";
   base = Substitute(base,"%1",cont);
   base = Substitute(base,"%2",System.capabilities.language);
   getURL(base,"_blank");
}
function frame_DP_init()
{
   function install_init()
   {
      var obj = new Object();
      obj.start = true;
      AlertResponse = obj;
   }
   windowTitle.text = ls.installerTitle;
   setStyle(windowTitle);
   checkPort(windowTitle);
   install_init();
   var errorCode = 0;
   this._x = xOffset;
   this._y = yOffset;
   killHand();
   stop();
   if(inputState.param == "" || inputState.param == undefined)
   {
      inputState.param = ls.installMsg_paramDefault;
   }
   sentence = ls.installMsg_confirm;
   serviceStyle = "<font color=\'#0066CC\'>" + inputState.param + "</font>";
   sentence = Substitute(sentence,"%1",serviceStyle);
   DP_msg.htmlText = sentence;
   setStyle(DP_msg);
   Yes.label.text = ls.Yes;
   setStyle(Yes.label);
   No.label.text = ls.No;
   setStyle(No.label);
   ec = new Array();
   ec[0] = ls.DP_error_0;
   ec[1] = ls.DP_error_1;
   ec[2] = ls.DP_error_2;
   ec[3] = ls.DP_error_3;
   ec[4] = ls.DP_error_4;
}
function download()
{
   var obj = new Object();
   AlertResponse = obj;
   if(obj.length == -1)
   {
      errorCode = 2;
      gotoAndStop(12);
      DP_msg.htmlText = ec[2];
      setStyle(DP_msg);
      clearInterval(checkIt);
   }
   else if(obj.length != 0)
   {
      progBar.bar._width = obj.position / obj.length * progBar.rect._width;
      if(obj.position == obj.length)
      {
         clearInterval(checkIt);
         obj.finish = true;
         AlertResponse = obj;
         if(obj.error == true)
         {
            errorCode = 3;
            gotoAndStop(12);
            DP_msg.htmlText = ec[3];
            setStyle(DP_msg);
            obj.finish = true;
            AlertResponse = obj;
         }
         else
         {
            AlertResponse = 0;
         }
      }
   }
   stop();
}
function frame_DP_download()
{
   windowTitle.text = ls.installerTitle;
   setStyle(windowTitle);
   checkPort(windowTitle);
   Cancel.label.text = ls.Cancel;
   setStyle(Cancel.label);
   DP_msg.htmlText = ls.installMsg_progress;
   setStyle(DP_msg);
   stop();
}
function frame_DP_error()
{
   windowTitle.text = ls.installerTitle;
   setStyle(windowTitle);
   checkPort(windowTitle);
   Retry.label.text = ls.Retry;
   setStyle(Retry.label);
   Cancel.label.text = ls.Cancel;
   setStyle(Cancel.label);
   stop();
}
function DP_dl_accept()
{
   var checkIt;
   checkIt = setInterval(this.download,200);
   gotoAndStop(13);
}
function DP_dl_deny()
{
   AlertResponse = 1;
}
stop();
if(inputState.domain == null)
{
   inputState = new Object();
   inputState.domain = "somesiteintheworld.com";
}
gScope = "/support/flashplayer/sys";
myRoot = this;
domainSettings = SharedObject.getLocal(inputState.domain + "/settings",gScope);
playerSettings = SharedObject.getLocal("settings",gScope);
movInit();
checkMode();
myMenuInit = function(menuObj)
{
   stop();
   menuObj.menu = false;
   menuObj.onSel = false;
   menuObj.selected = myDef;
   menuObj.sel.title.text = menuObj.selected;
   menuObj.displayAs = new Array();
};
drawMenu = function(p)
{
   activeMenu = p;
   p.menu = true;
   i = 0;
   while(i < p.mylist.length)
   {
      p.displayAs[i] = p.myList[i];
      p.attachMovie("item",i,i);
      p[i]._y = p.sel._height + p.sel._height * i;
      p[i]._x = 0;
      p[i].title.text = p.displayAs[i];
      i++;
   }
   p.lastClip = p.myList.length - 1;
};
killClips = function(o)
{
   i = 0;
   while(i < o.myList.length)
   {
      removeMovieClip(o[i]);
      i++;
   }
   o.menu = false;
   activeMenu = null;
};
showSel = function(obj, parent)
{
   parent.sel.title.text = parent.displayAs[obj._name];
   setSo(parent.displayAs[obj._name],parent);
   killClips(parent);
};
setSo = function(val, parent)
{
   if(parent._name == "cammenu")
   {
      myRoot.playerSettings.data.defaultCamera = val;
      myRoot.AlertResponse = "defaultCamera";
      myview.Clear();
   }
   else if(parent._name == "micmenu")
   {
      myRoot.playerSettings.data.defaultMicrophone = val;
      myRoot.AlertResponse = "defaultMicrophone";
   }
};
arrowAction = function(obj)
{
   if(!obj._parent.menu)
   {
      drawMenu(obj._parent);
   }
   else
   {
      killClips(obj._parent);
   }
};
outsideMenu = function(obj, par)
{
   var point = new Object();
   point.x = obj._xmouse;
   point.y = obj._ymouse;
   obj.localToGlobal(point);
   if(!obj.hitTest(point.x,point.y,false) && par.onSel == false && activeMenu != null)
   {
      var x = 0;
      while(x < par.myList.length)
      {
         removeMovieClip(par[x]);
         x++;
      }
      activeMenu = null;
      par.menu = false;
   }
};
killHand();
