package com.lecheng.protectAnimals.pojo;


public class UrlTask {
private String url;
private String fromUrl;

    public UrlTask(String url, String fromUrl) {
        this.url = url;
        this.fromUrl = fromUrl;
    }

    public UrlTask() {
    }

    public UrlTask(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj==null){
          return false;
      }
      UrlTask otherUrlTask = (UrlTask)obj;
      if (this.url.equals(otherUrlTask.getUrl())){
          return true;
      }
      return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fromUrl == null) ? 0 : fromUrl.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }
}
