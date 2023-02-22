import { Ref } from 'vue'

export interface Todo {
  id: number;
  content: string;
}

export interface Meta {
  totalCount: number;
}

export interface Column {
  field: string,
  label: string,
  type: string,
  listApi?: string,
  value: Ref<string>
}

export interface Driver {
  username: string,
  password: string,
  date: string,
}
