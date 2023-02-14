1. Build apps sosmed ini dengan docker image menggunakan dockerfile yang sudah ada.

2. Kemudian push image apps sosmed ini ke dockerhub milik anda.

3. Gunakan docker image mysql:5.7 untuk databasenya.

4. Restore database menggunakan file dump.sql
https://medium.com/@madushagunasekara/export-mysql-db-dump-from-kubernetes-pod-and-restore-mysql-db-on-kubernetes-pod-6f4ecc6b5a64

5. Gunakan environment variable supaya apps bisa terhubung dengan database.
- DB_HOST
- DB_PASS
- DB_USER
- DB_NAME

6. Baca dokumentasi mysql di dockerhub apabila diperlukan, dan coba andalkan google ketika anda merasa kesulitan.
