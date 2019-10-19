public class Consumer extends Thread{
    private Resource resource;

    public Consumer(){}

    public Consumer(Resource resource , String name){
        this.setName(name);
        this.resource = resource;

    }

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(500);
                resource.consume();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
