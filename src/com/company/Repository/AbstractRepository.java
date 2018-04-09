package com.company.Repository;

import com.company.Domain.*;
import com.company.Validatoare.*;

import java.util.TreeMap;
import java.util.Map;

public abstract class AbstractRepository<E extends HasID<ID>,ID> implements Repository<E,ID> {
   protected Map<ID,E> all=new TreeMap<>();
   protected Validator<E> validator;
   public AbstractRepository(Validator<E> validator)
   {
       this.validator=validator;
   }


}
