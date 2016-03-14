package pattern.chain;

import java.util.Map;

public abstract class ModifyChain
{
    public ModifyChain nextChain;

    public void setNextChain(ModifyChain chain)
    {
        this.nextChain = chain;
    }

    public abstract void proceed(Map map);
}
