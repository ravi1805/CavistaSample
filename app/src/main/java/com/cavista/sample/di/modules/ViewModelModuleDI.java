package com.cavista.sample.di.modules;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.cavista.sample.domain.usecase.SearchItemUseCase;
import com.cavista.sample.presentation.utils.ViewModelFactory;
import com.cavista.sample.presentation.viewmodel.SearchViewModel;
import com.cavista.sample.service.INetworkClientService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModuleDI {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        @NonNull Class<? extends ViewModel> value();
    }

    @NonNull
    @Provides
    ViewModelFactory viewModelFactory(@NonNull Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @NonNull
    @Provides
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    ViewModel searchViewModel(@NonNull SearchItemUseCase searchItemUseCase, INetworkClientService iNetworkClientService) {
        return new SearchViewModel(searchItemUseCase, iNetworkClientService);
    }

}
