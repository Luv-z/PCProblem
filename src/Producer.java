public class Producer extends Thread{

    private Resource resource;

    public Producer(){}

    public Producer(Resource resource , String name){
        this.setName(name);
        this.resource = resource;
    }

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(300);
                resource.produce();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
