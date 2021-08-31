package com.notfromus.viewmodel;

import com.notfromus.models.ModelFactory;

public class VMFactory {

    private MainItemViewModel mainItemViewModel;

    private View0ViewModel view0ViewModel;

    private ModelFactory modelFactory;

    private View2ViewModel view2ViewModel;

    private View3ViewModel view3ViewModel;

    public VMFactory(ModelFactory modelFactory){
        this.modelFactory = modelFactory;
        this.view0ViewModel = new View0ViewModel(this.modelFactory);
        this.mainItemViewModel = new MainItemViewModel();
        this.view2ViewModel = new View2ViewModel(this.modelFactory);
        this.view3ViewModel = new View3ViewModel(this.modelFactory);
    }

    public ModelFactory getModelFactory() {
        return modelFactory;
    }

    public View2ViewModel getView2ViewModel() {
        return view2ViewModel;
    }

    public MainItemViewModel getMainItemViewModel() {
        return mainItemViewModel;
    }

    public View0ViewModel getView0ViewModel() {
        return view0ViewModel;
    }

    public View3ViewModel getView3ViewModel() {
        return view3ViewModel;
    }
}

