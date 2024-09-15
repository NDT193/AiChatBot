package ApiService;

public interface ResponeApi {

    void onResponse(String respone);

    void onError(Throwable throwable);
}
