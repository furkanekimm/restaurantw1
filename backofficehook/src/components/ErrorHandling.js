import { NotificationManager } from 'react-notifications';

export const createNotification = (errorCode, type, lang) => {
  if (lang.lang === "en") {
    switch (errorCode) {
      case 1:
        NotificationManager.info('NAME NOT FOUND');
        break;
      case 2:
        NotificationManager.success('ID NOT FOUND');
        break;
      case 3:
        NotificationManager.warning('RECORD NOT FOUND');
        break;
      case 4:
        NotificationManager.success('Lutfen Id Giriniz');
        break;
      case 5:
        NotificationManager.warning('MEDIA SHOULD HAVE IN RECORD');
        break;
      case 6:
        NotificationManager.error('LIST IS EMPTY');
        break;
      case 200:
        if (type === 'add') {
          NotificationManager.success('Save is succesfully.');
        }
        if (type === 'delete') {
          NotificationManager.success('Delete is succesfully.');
        }
        if (type === 'update') {
          NotificationManager.success('Update is succesfully.');
        }
        break;
    }
  }
  if (lang.lang ==="tr") {
    switch (errorCode) {
      case 1:
        NotificationManager.info('ISIM BULUNAMADI.ISIM EKLEYİNİZ');
        break;
      case 2:
        NotificationManager.success('ID BULUNAMADI LUTFEN ID GİRİNİZ');
        break;
      case 3:
        NotificationManager.warning('KAYIT BULUNAMADI');
        break;
      case 4:
        NotificationManager.success('Lutfen Id Giriniz');
        break;
      case 5:
        NotificationManager.warning('KAYIT MEDYASI BULUNAMADI');
        break;
      case 6:
        NotificationManager.error('KAYITLAR BOŞ');
        break;
      case 200:
        if (type === 'add') {
          NotificationManager.success('KAYIT ISLEMI BASARILI');
        }
        if (type === 'delete') {
          NotificationManager.success('SILME ISLEMI BASARILI');
        }
        if (type === 'update') {
          NotificationManager.success('UPDATE ISLEMI BASARILI');
        }
        break;
    }
  }

};